package com.io.ssm.module.zookeeper.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

public class ZooDistributeLock implements Watcher {

    private static final Logger LOG = LoggerFactory.getLogger(ZooDistributeLock.class);

    private static final String LOCK_PATH = "/lock";

    // 模拟开启的线程数
    private static final int THREAD_NUM = 5;

    // 用于等待所有线程都连接成功后再执行任务
    private static CountDownLatch startFlag = new CountDownLatch(1);

    // 用于确保所有线程执行完毕
    private static CountDownLatch threadFlag = new CountDownLatch(THREAD_NUM);

    private ZooKeeper zk = null;

    private String currentPath;

    private String lockPath;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread() {
                @Override
                public void run() {
                    ZooDistributeLock zooDistributeLock = new ZooDistributeLock();
                    try {
                        zooDistributeLock.connection();
                        zooDistributeLock.createNode();
                        zooDistributeLock.getLock();
                    } catch (IOException | InterruptedException | KeeperException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        try {
            threadFlag.await();
            LOG.info("所有线程执行完毕...");
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void process(WatchedEvent event) {

        Event.KeeperState state = event.getState();
        Event.EventType type = event.getType();

        if (Event.KeeperState.SyncConnected == state) {
            if (Event.EventType.None == type) {
                // 标识连接成功
                LOG.info("成功连接上ZK服务器");
                startFlag.countDown();
            }

            if (Event.EventType.NodeDeleted == type && event.getPath().equals(this.lockPath)) {
                LOG.info("节点" + this.lockPath + "的锁已经被释放");
                try {
                    // 上一个节点释放了,当前节点去获取锁
                    getLock();
                } catch (KeeperException | InterruptedException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

    }

    /**
     * 连接到 ZK
     *
     * @throws IOException
     */
    private void connection() throws IOException, InterruptedException {

        zk = new ZooKeeper("127.0.0.1:2181", 3000, this);

        // 等待连接成功后再执行下一步操作
        startFlag.await();
    }

    // 创建节点,并初始化当前路径
    private void createNode() throws KeeperException, InterruptedException, UnsupportedEncodingException {
        this.currentPath = this.zk.create(LOCK_PATH, "".getBytes("UTF-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode
                .EPHEMERAL_SEQUENTIAL);
    }

    private void getLock() throws KeeperException, InterruptedException {
        if (minNode()) {
            doSomething();
            // 释放锁
            releaseLock();
        }
    }

    /**
     * 当前是否为最小节点
     *
     * @return
     */
    private boolean minNode() {

        // 当前序号
        try {
            initLockPath();
            // 判断前一个节点存在不存在,如果存在,则表示当前节点不是最小节点
            // zk.getData(this.lockPath, this, new Stat());
            zk.getData(this.lockPath, true, new Stat());
            LOG.info(this.currentPath + " 不是最小值,没有获取锁,等待 " + this.lockPath + " 释放锁");
            return false;
        } catch (KeeperException e) {
            LOG.info(this.currentPath + " 是最小值,获得锁");
            return true;
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
        return true;
    }

    private void doSomething() {
        LOG.info("处理业务逻辑...");
    }

    /**
     * 释放锁并关闭连接
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void releaseLock() throws KeeperException, InterruptedException {
        Thread.sleep(2000);
        if (this.zk != null) {
            LOG.info(this.currentPath + " 业务处理完毕,释放锁...");
            zk.delete(this.currentPath, -1);
            this.zk.close();
            LOG.info(Thread.currentThread().getName() + "关闭 zookeeper 连接");
        }
        threadFlag.countDown();
    }

    /**
     * 初始化 lockpath
     */
    private void initLockPath() {

        int currentSeq = Integer.parseInt(this.currentPath.substring(LOCK_PATH.length()));

        // 上一个序号
        int preSeq = currentSeq - 1;

        String preSeqStr = String.valueOf(preSeq);
        while (preSeqStr.length() < 10) {
            preSeqStr = "0" + preSeqStr;
        }
        this.lockPath = LOCK_PATH + preSeqStr;
    }

    @Override
    public String toString() {
        return "ZooDistributeLock{" +
                "zk=" + zk +
                ", currentPath='" + currentPath + '\'' +
                ", lockPath='" + lockPath + '\'' +
                '}';
    }
}
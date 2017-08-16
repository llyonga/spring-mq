package com.io.ssm.module.zookeeper.demo;

import org.apache.zookeeper.*;

/**
 * Created by 小五儿 on 2017-07-31
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        //创建于服务器的一个连接
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("触发了事件："+event.getType());
            }
        });

        //创建一个节点
        zooKeeper.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        // 创建一个子目录节点
        zooKeeper.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println(new String(zooKeeper.getData("/testRootPath",false,null)));
        // 取出子目录节点列表
        System.out.println(zooKeeper.getChildren("/testRootPath",true));

        System.out.println("*************************************");

        // 删除子目录节点
        zooKeeper.delete("/testRootPath/testChildPathTwo",-1);
        zooKeeper.delete("/testRootPath/testChildPathOne",-1);
        // 删除父目录节点
        zooKeeper.delete("/testRootPath",-1);
        // 关闭连接
        zooKeeper.close();

    }
}

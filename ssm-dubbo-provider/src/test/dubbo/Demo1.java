package dubbo;

import org.apache.zookeeper.*;

/**
 * Created by 小五儿 on 2017-07-31
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        //创建于服务器的一个连接
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("触发了事件："+event.getType());
            }
        });

        //System.out.println(new String(zooKeeper.getData("/dubbo",false,null)));


        // 取出子目录节点列表
        System.out.println("获取/dubbo下的子节点：*************************");
        System.out.println(zooKeeper.getChildren("/dubbo",true));

        System.out.println("*************************************");

        // 关闭连接
        zooKeeper.close();

    }
}

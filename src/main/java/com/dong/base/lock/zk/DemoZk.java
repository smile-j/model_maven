package com.dong.base.lock.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * PERSISTENT                持久化节点
 * PERSISTENT_SEQUENTIAL     顺序自动编号持久化节点，这种节点会根据当前已存在的节点数自动加 1
 * EPHEMERAL                 临时节点， 客户端session超时这类节点就会被自动删除
 * EPHEMERAL_SEQUENTIAL      临时自动编号节点
 */
public class DemoZk {

    private static final int TIMEOUT = 3000;
    public static String  Url="127.0.0.1:2181";   // 自行修改为自己搭建的zookeeper的ip和端口号

    @Test
    public void testpNode() throws IOException, InterruptedException {
        ZooKeeper zkp = new ZooKeeper(Url, TIMEOUT, null);
        zkp.close();
    }

    public static void main(String[] args) throws IOException {
        ZooKeeper zkp = new ZooKeeper(Url, TIMEOUT, null);
        try {
            // 创建一个EPHEMERAL类型的节点，会话关闭后它会自动被删除
            zkp.create("/node1", "data1".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            if (zkp.exists("/node1", false) != null) {
                System.out.println("node1 exists now.");
            }
            try {
                // 当节点名已存在时再去创建它会抛出KeeperException(即使本次的ACL、CreateMode和上次的不一样)
                zkp.create("/node1", "data1".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            } catch (KeeperException e) {
                System.out.println("KeeperException caught:" + e.getMessage());
            }

            // 关闭会话
            zkp.close();

            zkp = new ZooKeeper(Url, TIMEOUT, null);
            //重新建立会话后node1已经不存在了
            if (zkp.exists("/node1", false) == null) {
                System.out.println("node1 dosn't exists now.");
            }
            //创建SEQUENTIAL节点,其实在实际中，这几个节点的名称都是不一样的，自动增长node-0000000008，node-0000000009，node-0000000010
            zkp.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
            zkp.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
            zkp.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
            List<String> children = zkp.getChildren("/", null);
            System.out.println("Children of root node:");
            for (String child : children) {
                System.out.println(child);
            }

            zkp.close();
        } catch (Exception e) {
            System.out.println("发生错误："+e.getMessage());
        }
    }


}

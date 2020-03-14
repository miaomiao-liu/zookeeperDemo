import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;


public class ZkApiTest {

    @Test
    public void test() throws Exception {
        // 1、创建zookeeper连接
        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.9:2181", 2000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("触发了" + watchedEvent.getType());
            }
        });

        // 2、创建父节点
//        String path = zooKeeper.create("/liuyiqing", "miaoValue".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println(path);

        // 3、创建子节点
//        String childrenpath = zooKeeper.create("/liuyiqing/children", "childrenValue".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println(childrenpath);

        // 4、获取节点中的值
        // 父节点
        byte[] data = zooKeeper.getData("/liuyiqing", false, null);
        System.out.println(new String(data));

        //子节点
        List<String> children = zooKeeper.getChildren("/liuyiqing", false);
        for (String child : children) {
            System.out.println(child);
        }

        // 5、修改节点值
        Stat stat = zooKeeper.setData("/liuyiqing", "miaoValueUpdate".getBytes(), -1);
        System.out.println(stat);

        // 6、判断节点是否存在
        Stat exists = zooKeeper.exists("/liuyiqing/children", false);
        System.out.println(exists);


        // 7、删除节点
        zooKeeper.delete("/liuyiqing/children", -1);



    }
}

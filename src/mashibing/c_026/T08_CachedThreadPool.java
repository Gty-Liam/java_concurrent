package mashibing.c_026;

import org.omg.Messaging.SyncScopeHelper;

import java.util.concurrent.*;

public class T08_CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService es = Executors.newCachedThreadPool();
        ThreadPoolExecutor es = (ThreadPoolExecutor)Executors.newCachedThreadPool();  //个人想到的修改过期时间的方法
        es.setKeepAliveTime(10L, TimeUnit.SECONDS);
        System.out.println(es);

        for(int i=0;i<2;i++) {
            es.submit(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(es);
        System.out.println("休息1s");
        Thread.sleep(1000);
        System.out.println(es);
        es.submit(()->{
            System.out.println("是否会使用闲置线程，还是重新开启一个新的线程");
            System.out.println(es);
        });
        Thread.sleep(11000);
        System.out.println(es);
    }
}

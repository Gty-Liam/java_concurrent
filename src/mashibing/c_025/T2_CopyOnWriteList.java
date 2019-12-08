package mashibing.c_025;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class T2_CopyOnWriteList {
    static List list =
//            new CopyOnWriteArrayList();  // 107987ms
            new Vector();  //  170ms
//            new ArrayList(); // 线程不安全  //148ms

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        Random random = new Random();
        Runnable runnable = ()-> {
            for(int i=0;i<5000;i++) list.add("a" + random.nextInt(100000));
            latch.countDown();
        };
        for(int i=0;i<threads.length;i++) threads[i] = new Thread(runnable);
        Long start = System.currentTimeMillis();
        for(int i=0;i<threads.length;i++) threads[i].start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start) + "  list.size = " + list.size());
    }
}

package mashibing.c_025;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T1_ConcurrentMap {
    static Map map =
            Collections.synchronizedMap(new HashMap<>()); //268ms
//            new ConcurrentHashMap(); //232ms
//            new ConcurrentSkipListMap(); //265ms
//            new Hashtable();  //282ms

    public static void main(String[] args) {
        Random random = new Random();
        Thread[] threadArr = new Thread[30];
        CountDownLatch latch = new CountDownLatch(threadArr.length);
        Runnable runnable = ()->{
            for(int i=0;i<10000;i++){
                map.put("key_" + random.nextInt(10000000), "value_" + random.nextInt(10000000));
            }
            latch.countDown();
        };

        for(int i=0;i<threadArr.length;i++) threadArr[i] = new Thread(runnable);
        Long startTime = System.currentTimeMillis();
        Arrays.asList(threadArr).forEach(Thread::start);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("总共耗时：" + (endTime - startTime));
    }
}

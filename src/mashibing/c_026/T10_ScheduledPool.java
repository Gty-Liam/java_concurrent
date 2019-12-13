package mashibing.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T10_ScheduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(5);
        es.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread().getName());
        }, 2, 2, TimeUnit.SECONDS);
    }
}

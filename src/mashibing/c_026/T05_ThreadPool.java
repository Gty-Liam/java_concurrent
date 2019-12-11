package mashibing.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T05_ThreadPool {
    public static void main(String[] args) {
        Runnable r = ()->{
            System.out.println(Thread.currentThread().getName());
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i=0;i<7;i++){
            executorService.execute(r);
        }
        System.out.println("executorService : " + executorService);
        executorService.shutdown();
        System.out.println("is shut down? : " + executorService.isShutdown());
        System.out.println("is terminated? : " + executorService.isTerminated());
        System.out.println("executorService : " + executorService);

        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("sleep 2s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("executorService : " + executorService);
        System.out.println("is shut down? : " + executorService.isShutdown());
        System.out.println("is terminated? : " + executorService.isTerminated());
    }
}

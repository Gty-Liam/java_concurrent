package mashibing.c_026;

import java.util.concurrent.*;

public class T06_FutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(()->{
            System.out.println("execute future task.");
            Thread.sleep(2000);
            return 1000;
        });

        new Thread(futureTask).start();
        System.out.println(futureTask.get());  //会阻塞直等到futureTask任务执行完return结果

        //**********
        ExecutorService es = Executors.newFixedThreadPool(2);
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("execute future task.");
                Thread.sleep(2000);
                return 1000;
            }
        };

        FutureTask ft = (FutureTask) es.submit(callable);
        System.out.println(ft.get());  //会阻塞直等到futureTask任务执行完return结果
        //*******************
//        ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> future = executorService.submit(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                return 10086;
            }
        });
        System.out.println(future.isDone());
        System.out.println(future.isCancelled())    ;
        System.out.println(future.get());
        System.out.println(future.isDone());
    }
}

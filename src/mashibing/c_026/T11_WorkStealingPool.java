package mashibing.c_026;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T11_WorkStealingPool {
    static CountDownLatch latch = new CountDownLatch(6);
    public static void main(String[] args) throws IOException {
        // 根据cpu是几核来开启几个线程
//        ExecutorService service = Executors.newFixedThreadPool(4);
        ExecutorService service = Executors.newWorkStealingPool();
        // 查看当前计算机是几核
        System.out.println(Runtime.getRuntime().availableProcessors());

        long start = System.currentTimeMillis();
        service.execute(new R(1000, "Task1"));
        service.execute(new R(5000, "Task2"));
        service.execute(new R(6000, "Task3"));
        service.execute(new R(1000, "Task4"));
        service.execute(new R(5000, "Task5"));
        service.execute(new R(5000, "Task6"));

        // WorkStealing是精灵线程(守护线程、后台线程)，主线程不阻塞，看不到输出。
        // 虚拟机不停止，守护线程不停止
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        System.in.read();
    }

    static class R implements Runnable {
        int time;
        String name;
        public R(int time, String name) {
            this.time = time;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " 正在执行 " + name + " , 需要耗时 " + time);
                TimeUnit.MILLISECONDS.sleep(time);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " - " + time + "ms:" + Thread.currentThread().getName());
        }
    }
}

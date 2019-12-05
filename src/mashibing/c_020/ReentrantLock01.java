package mashibing.c_020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可以代替 synchronized， 比synchronized更灵活
 */
public class ReentrantLock01 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        Thread thread = new Thread(() -> {
            try {
                reentrantLock.lock();
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "  is running.");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });

        Thread thread1 = new Thread(() -> {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "  is runnning.");
            reentrantLock.unlock();
        });

        thread.start();
        thread1.start();
    }
}

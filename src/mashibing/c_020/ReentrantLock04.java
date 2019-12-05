package mashibing.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 */
public class ReentrantLock04 {
    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while(true){
                lock.lock();
                System.out.println("T1");
                lock.unlock();
            }
        });
        Thread thread1 = new Thread(()->{
            while(true) {
                lock.lock();
                System.out.println("T2");
                lock.unlock();
            }
        });

        thread.start();
        thread1.start();
    }
}

package mashibing.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockInterruptibly
 */
public class ReentrantLock03 {
    ReentrantLock lock = new ReentrantLock();
    public void m1(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "  is sleeping.");
            Thread.currentThread().sleep(5000);
            System.out.println(Thread.currentThread().getName() + "  is over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void m2(){
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " - 得到鎖");
            lock.unlock();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 已经被打断");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLock03 lock03 = new ReentrantLock03();

        Thread thread = new Thread(lock03::m1);
        Thread thread1 = new Thread(lock03::m2);

        thread.start();
        thread1.start();

        TimeUnit.SECONDS.sleep(2);
        thread1.interrupt(); // 如果不是lockInterruptibly锁，该语句无效，但不会报错
        System.out.println("thread1 is interrupted.");
    }
}

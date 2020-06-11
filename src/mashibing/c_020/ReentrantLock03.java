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
            System.out.println(Thread.currentThread().getName() + " 已经被打断");
        }finally {
            lock.unlock();
        }
    }

    public void m2(){
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " - 得到鎖");
            lock.unlock();
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " 已经被打断");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLock03 lock03 = new ReentrantLock03();

        Thread t1 = new Thread(lock03::m1, "t1");
        Thread t2 = new Thread(lock03::m2, "t2");

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println("主线程休眠两2秒结束");
        t2.interrupt(); // 如果t2里不是lockInterruptibly锁，该语句无效，也不会报错
        System.out.println("主线程试图打断 t2.");
    }
}

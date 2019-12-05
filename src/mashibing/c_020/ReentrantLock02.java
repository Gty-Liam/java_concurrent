package mashibing.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * trylock() 尝试获取锁
 */
public class ReentrantLock02 {
    ReentrantLock lock = new ReentrantLock();
    public void m1(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "  is sleeping.");
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 立刻尝试获得锁，如果得到返回true；反之返回false；
     */
    public void m2(){
        boolean isLock = lock.tryLock();
        if (isLock) {
            System.out.println(Thread.currentThread().getName() + " - 得到鎖 - " + isLock);
            lock.unlock();
        }
        else System.out.println(Thread.currentThread().getName() + " - 未得到锁 - " + isLock);
    }

    /**
     * 在一段时间内，持续尝试获得锁，如果得到返回true；反之返回false；
     */
    public void m3(){
        boolean isLock = false;
        try {
            isLock = lock.tryLock(4, TimeUnit.SECONDS); //在4秒内持续尝试获得锁
            if (isLock) {
                System.out.println(Thread.currentThread().getName() + " - 得到鎖 - " + isLock);
            } else System.out.println(Thread.currentThread().getName() + " - 未得到锁 - " + isLock);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(isLock) {
                System.out.println(Thread.currentThread().getName() + " 释放锁");
                lock.unlock();
            } else {
                System.out.println(Thread.currentThread().getName() + " 未得到所，不释放锁");
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock02 lock02 = new ReentrantLock02();
        Thread thread = new Thread(lock02::m1);
        Thread thread1 = new Thread(lock02::m2);
        Thread thread2 = new Thread(lock02::m3);

        thread.start();
        thread1.start();
        thread2.start();
    }
}

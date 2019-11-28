package qianfeng.sourceConflict;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private static Integer ticketNum = 100;

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Runnable sell = () -> {
            while(ticketNum > 0){
                reentrantLock.lock();
                if(ticketNum > 0)
                    System.out.println("seller " + Thread.currentThread().getName() + " sells a ticket, there are " + --ticketNum + " tickets left." );
                reentrantLock.unlock();
            }
        };

        Thread t1  = new Thread(sell, "A");
        Thread t2  = new Thread(sell, "B");
        Thread t3  = new Thread(sell, "C");
        Thread t4  = new Thread(sell, "D");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

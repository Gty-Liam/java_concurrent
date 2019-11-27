package qianfeng.sourceConflict;

public class SynchronizedDemo {
    private static Integer ticketNum = 100;

    public static void main(String[] args) {
        Runnable sell = () -> {
            while(ticketNum > 0){
                synchronized(SynchronizedDemo.class){
                    System.out.println("seller " + Thread.currentThread().getName() + " sells a ticket, there are " + --ticketNum + " tickets left." );
                }
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

package qianfeng.sourceconflict;

public class SynchronizedFunction {
    private static Integer ticketNum = 100;

    public static void main(String[] args) {
        Runnable sell = () -> {
            while(ticketNum > 0){
                sellTickets();
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

    public synchronized static void sellTickets() {
        if(ticketNum > 0){
            System.out.println("seller " + Thread.currentThread().getName() + " sells a ticket, there are " + --ticketNum + " tickets left." );
        }
    }
}

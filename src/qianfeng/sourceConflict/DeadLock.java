package qianfeng.sourceConflict;

public class DeadLock {
    public static void main(String[] args) {
        Runnable r1 = ()->{
            synchronized ("A"){
                System.out.println("A线程持有了A锁，在等待B锁");
                try {
                    "A".wait();  //只能释放这个线程当前所持有的锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized("B") {
                    System.out.println("A线程持有了A、B锁");
                }
            }
        };

        Runnable r2 = ()->{
            synchronized ("B"){
                System.out.println("B线程持有了B锁，在等待A锁");
                synchronized ("A"){
                    System.out.println("B线程持有了A、B锁");
                    "A".notifyAll(); // A线程执行wait之后，进入的是A锁的等待队列
                }
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }
}

package qianfeng.method;

public class ThreadYield {
    public static void main(String[] args) {
        Runnable r = () -> {
            for(int i=0;i<10;i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                if(i==3){
                    Thread.yield();
                }
            }
        };

        Thread thread = new Thread(r);
        Thread thread1 = new Thread(r);
        thread.start();
        thread1.start();
    }
}

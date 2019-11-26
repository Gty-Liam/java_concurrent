package qianfeng.method;

public class ThreadPriority {
    public static void main(String[] args) {
        Runnable r = () -> {
            for(int i=0;i < 50;i++)
                System.out.println(Thread.currentThread().getName());
        };
        Thread thread1 = new Thread(r, "Thread 1");
        Thread thread2 = new Thread(r, "Thread 2");
        thread2.setPriority(8);
        thread1.setPriority(2);
        thread1.start();
        thread2.start();
    }
}
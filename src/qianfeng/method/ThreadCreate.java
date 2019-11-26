package qianfeng.method;

public class ThreadCreate {
    public static void main(String[] args) {
        // 自定义类
        MyThread thread1 = new MyThread();

        // 实现Runable接口1
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                for(int i=0;i<30;i++){
                    System.out.println("线程" + threadName + " : " + i);
                }
                System.out.println("子线程" + threadName + "执行完毕");
            }
        };
        Thread thread2 = new Thread(r1);

        // 外部实现Runable接口2
        MyRunable r2 = new MyRunable();
        Thread thread3 = new Thread(r2);

        //start
        thread1.start();
        thread3.start();
        thread2.start();
        System.out.println("主线程执行完毕");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        String threadName = currentThread().getName();
        for(int i=0; i<30 ; i++){
            System.out.println("线程" + threadName + " : " + i);
        }
        System.out.println("子线程" + threadName + "执行完毕");
    }
}

// 实现Runable接口
class MyRunable implements Runnable {
    public void run() {
        String threadName = Thread.currentThread().getName();
        for(int i=0;i<30;i++){
            System.out.println("线程" + threadName + " : " + i);
        }
        System.out.println("子线程" + threadName + "执行完毕");
    }
}

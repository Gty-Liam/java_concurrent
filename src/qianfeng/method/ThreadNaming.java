package qianfeng.method;

/**
 * 线程的命名
 */
public class ThreadNaming {
    public static void main(String[] args) {
        //1. 直接命名
        Thread thread = new Thread();
        thread.setName("Thread");

        //2. 使用构造函数
        Thread thread1 = new Thread(()->{}, "Thread 1");
        Thread thread2 = new Thread("Thread 2");

        //3. 继承Thread
        MyThread1 myThread1 = new MyThread1("MyThread");


        System.out.println(myThread1.getName());
    }
}

class MyThread1 extends Thread {
    public MyThread1(String name ){
        //方法1
        super(name);
        //方法2
//        this.setName(name);
    }
}
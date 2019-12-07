package qianfeng.designpattern;

import java.util.ArrayList;
import java.util.List;

public class SingletonTest3 {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            for(int i=0;i<20;i++) {
//                System.out.println("获取实例");
                Singleton3.getInstance();
            }
        };
        List<Thread> threadList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            threadList.add(new Thread(runnable));
        }
        threadList.forEach((o)->{o.start();});
        threadList.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();

        System.out.println("use time: " + (end - start) + "ms");
    }
}

class Singleton3 {
    private Singleton3(){
        System.out.println("实例化一个Singleton对象");
    }
    static class Inner {
        static private Singleton3 instance = new Singleton3();
    }
    static Singleton3 getInstance(){
        return Inner.instance;
    }
}

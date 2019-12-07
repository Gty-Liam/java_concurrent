package qianfeng.designpattern;

import sun.security.jca.GetInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个锁的粒度更小。理论上应该是这个效率会更高，但实际好像是这个慢一点
 */
public class SingletonTest2 {
    static class Singleton{
        private static Singleton instance = null;

        Singleton(){
            System.out.println("实例化Singleton实例");
        }

        public static Singleton getInstance(){
            if(instance == null){
                synchronized (Singleton.class){
                    if(instance == null){
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        Runnable runnable = ()->{
            for(int i=0;i<20;i++) {
                Singleton.getInstance();
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

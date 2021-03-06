package qianfeng.designpattern;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SingletonTest {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            for(int i=0;i<20;i++) {
//            System.out.println("获取实例");
                Singleton.getSingleton();
            }
        };
        List<Thread> threadList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            threadList.add(new Thread(runnable));
        }
        threadList.forEach(o->o.start());
        threadList.forEach(o-> {
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

class Singleton{
    private static Singleton instance = null;

    private Singleton(){
        System.out.println("实例化一个Singleton对象");
    }

    public static synchronized Singleton getSingleton(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}

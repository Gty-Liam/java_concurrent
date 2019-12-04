package mashibing.c_019;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 实现方法三  CountDownLatch
 * CountDownLatch不涉及锁，效率比notify高的多
 */
public class MyContainer4 {
    private List list = new ArrayList();

    public void add(){
        list.add(new Integer(1));
    }

    public Integer size(){
        return list.size();
    }

    public static void main(String[] args) {
        CountDownLatch listeningLatch = new CountDownLatch(1);
        CountDownLatch addingLatch = new CountDownLatch(1);
        MyContainer4 container = new MyContainer4();
        Thread listening = new Thread(() -> {
            try {
                listeningLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("stop  "  + container.size());
            addingLatch.countDown();
        });

        Thread adding = new Thread(()->{
            for(int i=0;i<10;i++){
                container.add();
                System.out.println("add one Integer.");
                if(container.size() == 5){
                    listeningLatch.countDown();
                    try {
                        addingLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        listening.start();
        adding.start();
    }
}



package mashibing.c_019;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现方式二  synchronized
 */
public class MyContainer2 {
    private List list = new ArrayList();

    public void add(){
        list.add(new Integer(1));
    }

    public Integer size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer2 container = new MyContainer2();
        Thread listening = new Thread(() -> {
            while (container.size() != 5) {
//                System.out.println("thread is running  " + container.size());
            }
            synchronized (container) {
                System.out.println("thread stop  " + container.size());
                container.notify();
            }
        });

        Thread adding = new Thread(()->{
            for(int i=0;i<10;i++){
                synchronized (container) {
                    container.add();
                    System.out.println("add one Integer.");
                    if (i == 4) {
                        try {
                            container.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        listening.start();
        adding.start();
    }
}



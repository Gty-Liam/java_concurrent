package mashibing.c_019;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现方式一  join等待
 */
public class MyContainer1 {
    private List list = new ArrayList();

    public void add(){
        list.add(new Integer(1));
    }

    public Integer size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer1 container = new MyContainer1();
        Thread listening = new Thread(() -> {
            while (container.size() != 5) {
//                System.out.println("thread is running  " + container.size());
            }
            System.out.println("thread stop  " + container.size());
        });

        Thread adding = new Thread(()->{
            for(int i=0;i<10;i++){
                container.add();
                System.out.println("add one Integer.");
                if(i == 4){
                    try {
                        listening.join();
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



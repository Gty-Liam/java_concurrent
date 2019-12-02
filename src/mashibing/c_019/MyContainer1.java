package mashibing.c_019;

import java.util.ArrayList;
import java.util.List;
public class MyContainer1 {
    private volatile List list = new ArrayList();

    public void add(){
        list.add(new Integer(1));
    }

    public Integer size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer1 container = new MyContainer1();
        Thread thread = new Thread(() -> {
            while (container.size() < 5) {
                System.out.println("thread is running  " + container.size());

            }
            System.out.println("thread stop" + container.size());
        });

        thread.start();
        for(int i=0;i<10;i++){
            container.add();
            System.out.println("add one Integer.");
        }
    }
}



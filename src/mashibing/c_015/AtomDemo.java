package mashibing.c_015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomDemo {
    AtomicInteger count = new AtomicInteger(0);

    //    public void increase(){
    public void increase(){
        for(int i=0;i<5000;i++) count.incrementAndGet();
    }

    public static void main(String[] args) {
        AtomDemo demo = new AtomDemo();
        List<Thread> threadList = new ArrayList<>();
        for(int i=0;i<10;i++){
            threadList.add(new Thread(demo::increase));
        }
        threadList.forEach(Thread::start);
        threadList.forEach(thread->{
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(demo.count);
    }
}

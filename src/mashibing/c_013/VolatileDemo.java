package mashibing.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * 为说明volatile不能保证原子性
 */
public class VolatileDemo {
    volatile Integer count = 0;

//    public void increase(){
    public synchronized void increase(){
        for(int i=0;i<5000;i++) count++;
    }

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
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

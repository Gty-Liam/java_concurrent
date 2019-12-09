package mashibing.c_025;

import com.sun.org.apache.xerces.internal.util.SymbolHash;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

public class T5_LinkedBlockingQueue {
    static LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<String>(20);

    private static void consumer(){
        while (true) {
            try {
                String name = (String) queue.take();
                System.out.println(Thread.currentThread().getName() + "消费 " + name + "， 剩余 " + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void product(){
        int i = 0;
        while(true) {
            try {
                String name = Thread.currentThread().getName() + "＿a＿" + i;
                queue.put(name);
                i++;
                System.out.println(Thread.currentThread().getName() + " 生产 " + name + "， 剩余 " + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ArrayBlockingQueue(10);
        int producerSize = 2;
        for(int i=0;i<producerSize;i++){
            new Thread(T5_LinkedBlockingQueue::product, "producer " + i).start();
        }
        for(int i=0;i<10;i++){
            new Thread(T5_LinkedBlockingQueue::consumer, "consumer " + i).start();
        }
    }
}

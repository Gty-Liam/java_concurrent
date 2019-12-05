package mashibing.c_021;

import java.util.ArrayList;
import java.util.List;

/**
 * 固定容量的同步容器，
 */
public class Container01 {
    private Integer MAX_SIZE = 10;
    private List container = new ArrayList();

    public void put() {
        container.add(1);
    }

    public void pop() {
        container.remove(0);
    }

    public synchronized Integer getSize() {
        return container.size();
    }

    public void product() {
        try {
            while (true) {
                synchronized (this) {
                    if (getSize() == MAX_SIZE) {
                        // 如果没有else：一个生产者在这里释放锁之后，接下来可能也是一个生产者抢到锁，然后也在这里释放锁。
                        // 那这个时候其实在这里等着的有两个生产者。而消费者唤醒等待队列之后，
                        // 抢到锁的生产者1会直接执行put，然后释放锁；然后生产者2又拿到了锁，直接执行put。所以会出现剩余11,12的情况
                        this.wait();
                    } else {
                        put();
                        System.out.println(Thread.currentThread().getName() + " 生产了一个对象， 剩余" + getSize());
                        this.notifyAll();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        try {
            while (true) {
                synchronized (this) {
                    if (getSize() == 0) {
                        this.wait();
                    } else {
                        pop();
                        System.out.println(Thread.currentThread().getName() + " 消费了一个对象， 剩余" + getSize());
                        this.notifyAll();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Container01 container = new Container01();

        for (int i = 0; i < 2; i++) {
            new Thread(container::product, "producer" + i).start();
        }
        for (int i = 0; i < 4; i++) {
            new Thread(container::consume, "consumer" + i).start();
        }
    }
}

package mashibing.c_021;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定容量的同步容器， 使用 ReentrantLock & Condition
 */
public class Container02 {
    private Integer MAX_SIZE = 10;
    private List container = new ArrayList();
    ReentrantLock lock = new ReentrantLock();
    Condition product = lock.newCondition();
    Condition consume = lock.newCondition();

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
                lock.lock();
                if (getSize() == MAX_SIZE) {
                    product.await();
                } else {
                    put();
                    System.out.println(Thread.currentThread().getName() + " 生产了一个对象， 剩余" + getSize());
                    consume.signalAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        try {
            while (true) {
                lock.lock();
                if (getSize() == 0) {
                    consume.await();
                } else {
                    pop();
                    System.out.println(Thread.currentThread().getName() + " 消费了一个对象， 剩余" + getSize());
                    product.signalAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Container02 container = new Container02();

        for (int i = 0; i < 2; i++) {
            new Thread(container::product, "producer" + i).start();
        }
        for (int i = 0; i < 4; i++) {
            new Thread(container::consume, "consumer" + i).start();
        }
    }
}

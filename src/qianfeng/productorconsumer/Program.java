package qianfeng.productorconsumer;

public class Program {
    public static void main(String[] args) {
        ProductPool productPool = new ProductPool(20);
        Consumer consumer = new Consumer(productPool);
        Productor productor = new Productor(productPool);

        Thread consumerThread = new Thread(consumer);

        productor.start();
        consumerThread.start();
    }
}
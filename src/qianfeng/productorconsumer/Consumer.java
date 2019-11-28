package qianfeng.productorconsumer;

public class Consumer implements Runnable{
    private ProductPool productPool;
    public Consumer(ProductPool productPool){
        this.productPool = productPool;
    }

    @Override
    public void run() {
        while(true){
            productPool.pop();
        }
    }
}

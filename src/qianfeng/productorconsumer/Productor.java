package qianfeng.productorconsumer;

import java.util.Random;

public class Productor extends Thread{
    private ProductPool productPool;
    public Productor(ProductPool productPool) {
        this.productPool = productPool;
    }

    @Override
    public void run() {
        while(true){
            productPool.push();
        }
    }
}

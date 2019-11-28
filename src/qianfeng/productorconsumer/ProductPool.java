package qianfeng.productorconsumer;

import sun.security.util.HostnameChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductPool {
    private Integer maxSize;
    private List<Product> productList;

    public ProductPool(){
        maxSize = 10;
        productList = new ArrayList<>();
    }

    public ProductPool(Integer maxSize){
        this.maxSize = maxSize;
        productList = new ArrayList<>();
    }

    public synchronized void push() {
        if(productList.size() == maxSize){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Product product = new Product("No. " + new Random().nextInt(80));
        productList.add(product);
        System.out.println("push √√ <" + product.getName() + "> into pool, there are(is) " + productList.size() + " product(s) left.");
        this.notifyAll();
    }

    public synchronized void pop() {
        if(productList.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Product product = productList.remove(0);
        System.out.println("pop ×× <" + product.getName() + "> into pool, there are(is) " + productList.size() + " product(s) left.");
        this.notifyAll();
    }

    public Integer getMaxSize(){
        return maxSize;
    }

    public Integer getProductSize(){
        return productList.size();
    }
}

package mashibing.c_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class T12_ForkJoinPool {
    static int[] num_arr;
    final static int SIZE = 1000000;
    static int total = 0;  //共享资源  需要上锁
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(Arrays.stream(num_arr).sum());
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(0, SIZE);
        new ForkJoinPool().execute(myRecursiveAction);
        TimeUnit.SECONDS.sleep(2);
        System.out.println(total);
        System.in.read();

    }
    static  {
        Random r = new Random();
        num_arr = new int[SIZE];
        for(int i=0;i<num_arr.length;i++){
            num_arr[i] = r.nextInt(100);
        }
    }

    static class MyRecursiveAction extends RecursiveAction{
        int startIndex, endIndex;
        final int MAX_NUM = 5000;

        /**
         * 计算数组[startIndex, endIndex)的和
         * @param startIndex 闭区间
         * @param endIndex 开区间
         */
        public MyRecursiveAction(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        protected void compute() {
            if(endIndex - startIndex <= MAX_NUM){
                long sum = 0;
                for(int i=startIndex;i<endIndex;i++){
                    sum += num_arr[i];
                }
                lock.lock();
                total += sum;  //现在局部变量sum里相加，最后再一次性加到total上，上锁的粒度小，效率会高一点
                lock.unlock();
//                System.out.println("[" + startIndex + ", " + endIndex + "] 的总和 = " + sum);
            } else{
                int middle = (startIndex + endIndex) / 2;
                MyRecursiveAction task1 = new MyRecursiveAction(startIndex, middle);
                MyRecursiveAction task2 = new MyRecursiveAction(middle, endIndex);
                task1.fork();
                task2.fork();
            }
        }
    }
}

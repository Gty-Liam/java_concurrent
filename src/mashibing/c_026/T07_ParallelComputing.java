package mashibing.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class T07_ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();
        List<Integer> primes = getPrimeList(1, 50000000);
        Long end = System.currentTimeMillis();
        System.out.println("耗时： " + (end - start));
////        System.out.println("素数: " + primes);
        System.out.println("******************************");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("******************************");
        ExecutorService es = Executors.newFixedThreadPool(4);
        MyCallable task1 = new MyCallable(1, 20000000);
        MyCallable task2 = new MyCallable(20000000, 36000000);
        MyCallable task3 = new MyCallable(36000000, 44000000);
        MyCallable task4 = new MyCallable(44000000, 50000000);
        Long start1 = System.currentTimeMillis();
        Future f1 = es.submit(task1);
        Future f2 = es.submit(task2);
        Future f3 = es.submit(task3);
        Future f4 = es.submit(task1);
        f1.get();f2.get();f3.get();f4.get();

        //下面的写法会一级级阻塞
//        es.submit(task1).get();
//        es.submit(task2).get();
//        es.submit(task3).get();
//        es.submit(task1).get();
        Long end1 = System.currentTimeMillis();
        System.out.println("耗时： " + (end1 - start1));
//        System.out.println(f1.get().toString() + f2.get() + f3.get() + f4.get());
    }

    public static boolean isPrime(int num){
        if(num == 1) return false;
        for(int i=2; i<((int)Math.sqrt(num)+1); i++){
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * 获取范围内的素数[start, end)
     * @return 素数列表 (ArrayList)
     */
    public static List<Integer> getPrimeList(int start, int end){
        List<Integer> primes = new ArrayList<Integer>();
        for(int i=start;i<end;i++){
            if(isPrime(i))
                primes.add(i);
        }
        return primes;
    }


}

class MyCallable implements Callable{
    Integer start, end;

    public MyCallable(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Object call() throws Exception {
        return T07_ParallelComputing.getPrimeList(start, end);
    }
}
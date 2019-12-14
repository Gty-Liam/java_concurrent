package mashibing.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class T14_ParallelStreamAPI {
    public static void main(String[] args) {
        Random r = new Random();
        List<Integer> numList = new ArrayList<>();
        for(int i=0;i<10000000; i++){
            numList.add(10000000 + r.nextInt(10000000));
        }

        Long start1 = System.currentTimeMillis();
        numList.forEach(num -> isPrime(num));
        Long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

        Long start2 = System.currentTimeMillis();
        numList.parallelStream().forEach(T14_ParallelStreamAPI::isPrime);
        Long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);
    }

    static public Boolean isPrime(int num){
        if(num <= 1)
            return false;
        for(int i=2;i<(Math.sqrt(num) + 1); i++){
            if(num % i == 0){
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}

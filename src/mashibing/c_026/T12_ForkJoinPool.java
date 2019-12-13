package mashibing.c_026;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class T12_ForkJoinPool {
    static int[] num_arr;

    public static void main(String[] args) {
        Arrays.stream(num_arr).sum();
    }
    static  {
        Random r = new Random();
        num_arr = new int[1000000];
        for(int i=0;i<num_arr.length;i++){
            num_arr[i] = r.nextInt(100);
        }
    }

    static class MyRecursiveAction extends RecursiveAction{
        int startIndex, endIndex;
        final int MAX_NUM = 100000;

        public MyRecursiveAction(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        protected void compute() {
            if(endIndex - startIndex <= MAX_NUM){
//                System.out.println(Arrays.stream(num_arr).sum());

            }
        }
    }
}

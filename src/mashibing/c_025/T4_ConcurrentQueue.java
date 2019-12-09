package mashibing.c_025;

import java.security.cert.PolicyQualifierInfo;
import java.util.Queue;
import java.util.concurrent.*;

public class T4_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<Object> queue = new ConcurrentLinkedQueue<Object>();

        queue.add("String");
        queue.offer(1996);
        queue.offer('a');
        System.out.println(queue);
        System.out.println(queue.size());

        System.out.println(queue.remove('a'));
        System.out.println(queue.size());

        System.out.println(queue.poll());
        System.out.println(queue.size());

        System.out.println(queue.peek());
        System.out.println(queue.size());
    }
}

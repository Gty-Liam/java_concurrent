package mashibing.c_019;

import java.util.ArrayList;
import java.util.List;

/**
 * 空循环以致变量不可见性问题测试
 */
public class MyContainer3 {
    private List list = new ArrayList();
//    private volatile List list = new ArrayList();  // 空循环的时候不加volatile，会出现不可见性问题

    public void add(){
        list.add(new Integer(1));
    }

    public Integer size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer3 container = new MyContainer3();
        Thread listening = new Thread(() -> {
//            while (true) {
////                System.out.println("runnning "  + container.size());
//                if(container.size() == 5){
//                    System.out.println("stop");
//                    break;
//                }
//            }

            while (container.size() != 5) {
//                System.out.println("runnning "  + container.size());  //倘若没有这句输出，就会出现不可见性的问题，就需要加volatile了
            }
            System.out.println("stop  "  + container.size());
        });

        Thread adding = new Thread(()->{
            for(int i=0;i<10;i++){
                container.add();
                System.out.println("add one Integer.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        listening.start();
        adding.start();
    }
}



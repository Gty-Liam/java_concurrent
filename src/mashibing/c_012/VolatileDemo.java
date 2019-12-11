package mashibing.c_012;

/**
 * 为说明volatile的线程可见性
 */
public class VolatileDemo {
//    volatile static Boolean run = true;
    static Boolean run = true;

    public void running(){
        System.out.println("running...");
        Integer count = 0;
        while(run){
            try {
                count++;
//                if(count > 500){
//                    Thread.currentThread().sleep(10);
//                }
//                System.out.println(count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("stop...");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(()->new VolatileDemo().running());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("wake");
        run = false;
    }
}

package mashibing.c_022;

public class ThreadLocalDemo {
    private static String personName = "lisi";
    private static ThreadLocal<Integer> age = new ThreadLocal<>();

    public static void main(String[] args) {
        age.set(30);  // 对主线程的局部变量进行设置
        Thread t1 = new Thread(()->{
            try{
                Thread.sleep(300);
                System.out.println("t1 personName :" + personName);
                System.out.println("t1 age :" + age.get());
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            System.out.println("t2 set personName = Zhangsan and age = 40");
            personName = "Zhangsan";
            age.set(40);
            System.out.println("t2 personName ：" + personName);
            System.out.println("t2 age：" + age.get());
        });

        t2.start();
        t1.start();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main Thread age : " + age.get());
    }
}

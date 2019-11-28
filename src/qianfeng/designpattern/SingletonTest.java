package qianfeng.designpattern;

public class SingletonTest {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            Singleton.getSingleton();
        };

        for(int i=0;i<100;i++){
            new Thread(runnable).start();
        }
    }
}

class Singleton{
    private static Singleton instance = null;

    private Singleton(){
        System.out.println("实例化一个Singleton对象");
    }

    public static synchronized Singleton getSingleton(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}

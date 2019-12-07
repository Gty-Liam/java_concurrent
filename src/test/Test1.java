package test;

public class Test1 {
    private Test1(){
        System.out.println("实例化了一个Test1的对象");
    }
    private static class Inner{
        static Test1 instance = new Test1();
    }
    public static Test1 getInstance(){
        return Inner.instance;
    }
}

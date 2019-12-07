package test;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("程序开始");
        Test1 test1 = null;
        System.out.println("main 获取实例");
        test1 = Test1.getInstance();
        System.out.println("main 再获取实例");
        test1 = Test1.getInstance();
    }
}

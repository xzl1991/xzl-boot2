package jvm;

/**
 * @auther xzl on 18:22 2018/1/15
 */
public class TestJIT {
    public static void main(String[] args) {
        System.out.println("--开始---");
        for(int i=0;i<110;i++){
            test();
        }
        System.out.println("--结束---");
    }
    public static void test(){
        int a = 0;
    }
}

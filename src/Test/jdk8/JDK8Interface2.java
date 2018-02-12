package jdk8;

/**
 * @auther xzl on 15:15 2018/1/30
 */
public interface JDK8Interface2 {
    //1.接口中可以定义静态方法了
    static void staticMethod() {
        System.out.println("接口2中的静态方法");
    }
    //2.使用default 定义普通方法
    default void testDefault(){
        System.out.println("接口2中default方法");
    }
    //2.使用default 定义普通方法
    default String testDefaultString(){
        return "接口2定义的";
    }
}

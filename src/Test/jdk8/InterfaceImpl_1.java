package jdk8;

/**
 * @auther xzl on 15:24 2018/1/30
 */
public class InterfaceImpl_1 implements JDK8Interface1,JDK8Interface2{
    @Override
    public String testDefaultString() {
        return "实现了俩接口";
    }

    @Override
    public void testDefault() {
        System.out.println("实现俩接口");
    }
}

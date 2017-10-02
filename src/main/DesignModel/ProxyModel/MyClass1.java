package DesignModel.ProxyModel;

/**
 * Created by ${xzl} on 2017/8/23.
 */
public class MyClass1 implements MyInterface{
    public void method1(){
        System.out.println("---方法1");
    };

    public void method2(){
        System.out.println("---方法2");
    }

    public void method3(){
        System.out.println("---方法3");
    }

    @Override
    public void method4() {
        System.out.println("---方法4");
    }

    public void method5(){
        System.out.println("---方法5");
    }
}

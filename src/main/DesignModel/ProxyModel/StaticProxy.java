package DesignModel.ProxyModel;

/**
 * Created by ${xzl} on 2017/8/25.
 */
/**
 * 代理对象,静态代理
 */
public class StaticProxy {
    //接收保存目标对象
    private MyClass1 myClass1;
    public StaticProxy(MyClass1 myClass1){
        this.myClass1 = myClass1;
    }
    public void test (){
        System.out.println("before--静态代理方法--");
        myClass1.method1();
        System.out.println("after--静态代理方法--");
    }
}

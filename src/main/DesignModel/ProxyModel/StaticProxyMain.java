package DesignModel.ProxyModel;

/**
 * Created by ${xzl} on 2017/8/25.
 */
public class StaticProxyMain {
    public static void main(String[] args) {
        MyClass1 myclass = new MyClass1();
        StaticProxy proxy = new StaticProxy(myclass);
        proxy.test();//执行代理方法
    }
}

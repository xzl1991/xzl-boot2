package DesignModel.ProxyModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ${xzl} on 2017/8/25.
 */
public class ProxyJdk implements InvocationHandler{
    Object targect;

    public  ProxyJdk(Object targect){
        super();
        this.targect = targect;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("------------------before------------------");

        // 执行目标对象的方法
        Object result = method.invoke(targect, args);

        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("-------------------after------------------");
        return result;
    }

    /**
     * 获取目标对象的代理对象
     * @return 代理对象
     */
    public  Object getProxy(){
        return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), targect.getClass().getInterfaces(), this);
    }
}

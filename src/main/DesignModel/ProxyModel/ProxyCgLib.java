package DesignModel.ProxyModel;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * Created by ${xzl} on 2017/8/25.
 */
public class ProxyCgLib implements MethodInterceptor {
    private  Object target;
    public  ProxyCgLib (Object target){
        super();
        this.target = target;
    }
    /**
     * 获取目标对象的代理对象
     * @return 代理对象
     */
    public  Object getInstance(){
        //1.工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(target.getClass());
        //3.设置回调函数
//        enhancer.setCallback(this); //默认函数
        Callback[] callbacks = new Callback[]{};
        enhancer.setCallback(callbacks[0]);
        //4.创建子类(代理对象)
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("---------------cglib---before------------------");

        // 执行目标对象的方法
        Object result = method.invoke(target, args);

        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("---------------cglib----after------------------");
        return result;
    }
}

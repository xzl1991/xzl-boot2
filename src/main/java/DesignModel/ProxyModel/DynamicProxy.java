package DesignModel.ProxyModel;

import org.springframework.cglib.core.DebuggingClassWriter;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.*;

/**
 * Created by ${xzl} on 2017/8/23.
 */
public class DynamicProxy implements InvocationHandler{
    private Object source;

    public DynamicProxy(Object source) {
        super();
        this.source = source;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before---代理接口输出---");
        Method sourceMethod = source.getClass().getDeclaredMethod(method.getName(),method.getParameterTypes());//不通过接口 代理
//        sourceMethod.setAccessible(true);
        Object result = sourceMethod.invoke(source,args);
        System.out.println("after----结束----");
        return result;
    }

    public static void main(String[] args) throws NoSuchMethodException {
//        //只要你传入就可以强转成功
//        MyInterface object =  (MyInterface) Proxy.newProxyInstance(
//                ClassLoader.getSystemClassLoader(),//ClassLoader
//                new Class[]{MyInterface.class},//Class<?>[] interfaces,
//                new DynamicProxy(new MyClass())//InvocationHandler h
//        );
////        byte[] classFile = ProxyGenerator.generateProxyClass("TestProxy", new Class[]{MyInterface.class});//获取类文件
//        object.method1();
//        object.method2();
//        object.method3();
//
        //生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //获取动态代理类
        Class proxyClazz = Proxy.getProxyClass(MyInterface.class.getClassLoader(),MyInterface.class);
        //获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        //通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        try {
            MyInterface iHello = (MyInterface) constructor.newInstance(new ProxyJdk(new MyClass1()));
            iHello.method1();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //通过代理对象调用目标方法
//        //2.jdk 接口动态代理
        MyInterface myClass1 = new MyClass1();
        MyInterface proxyJdk = (MyInterface) new ProxyJdk(myClass1).getProxy();
        proxyJdk.method1();

        //3.cglib代理
        MyInterface proxyCgLib = (MyInterface) new ProxyCgLib(myClass1).getInstance();
        proxyCgLib.method2();

        byte[] classFile = ProxyGenerator.generateProxyClass("TestProxy", new Class[]{proxyCgLib.getClass()});
        File file = new File("E:/ProxyCgLib1.class");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(classFile);
            fos.flush();
            fos.close();
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\\\target\\\\classes");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

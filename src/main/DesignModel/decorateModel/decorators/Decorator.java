package main.DesignModel.decorateModel.decorators;

import main.DesignModel.decorateModel.interfaces.MyInterface;

/**
 * Created by ${xzl} on 2017/9/5.
 * 定义：
 * 装饰模式是在不必改变原类文件和使用继承的情况下，
 * 动态的扩展一个对象的功能。它是通过创建一个包装对象，也就是装饰来包裹真实的对象。
 */
public abstract class Decorator implements MyInterface{
    protected MyInterface myInterface;
    public Decorator(MyInterface myInterface){
        super();
        this.myInterface = myInterface;
    }
    @Override
    public void sayHello() {
        myInterface.sayHello();
    }
}

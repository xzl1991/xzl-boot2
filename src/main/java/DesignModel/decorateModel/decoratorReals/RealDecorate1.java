package DesignModel.decorateModel.decoratorReals;

import DesignModel.decorateModel.decorators.Decorator;
import DesignModel.decorateModel.interfaces.MyInterface;

/**
 * Created by ${xzl} on 2017/9/5.
 */
public class RealDecorate1 extends Decorator {
    public RealDecorate1(MyInterface myInterface) {
        super(myInterface);
    }
    public void beforeSay(){
        System.out.println("装饰器1,新增的方法....");
    }
    public void sayHello(){
        System.out.println("装饰器1,对原方法包装....");
        super.sayHello();
        System.out.println("装饰器1,自定义结束....");
    }
}














































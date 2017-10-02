package main.DesignModel.Factory.SimpleFactory;

import main.DesignModel.Factory.SimpleFactory.impl.ProductA;
import main.DesignModel.Factory.SimpleFactory.impl.ProductB;

/**
 * Created by ${xzl} on 2017/8/28.
 *<p>
 * 从设计模式的类型上来说，简单工厂模式是属于创建型模式，又叫做静态工厂方法（Static Factory      Method）模式，但不属于23种GOF设计模式之一。简单工厂模式是由一个工厂对象决定创建出哪一种产品 类的实例。简单工厂模式是工厂模式家族中最简单实用的模式，可以理解为是不同工厂模式的一个特殊实现
 *
 *</p>
 * 工厂类
 */
public class CreateFactory  {
    private CreateFactory(){};
    public  static  InterfaceProduct createProduct(String name){
        if(name==null||name.equals("")){
            throw new RuntimeException("产品名称不正确！");
        }
        switch (name){
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
        }
        return new ProductB();
    }

    public static void main(String[] args) {
        InterfaceProduct productByFactory = CreateFactory.createProduct("A");
        productByFactory.create();

        productByFactory = CreateFactory.createProduct("");
        productByFactory.create();

    }
}





























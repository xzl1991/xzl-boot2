package main.DesignModel.Factory.FactoryMethod;


/**
 * Created by ${xzl} on 2017/8/28.
 *<p>
 * 从设计模式的类型上来说，简单工厂模式是属于创建型模式，又叫做静态工厂方法（Static Factory      Method）模式，但不属于23种GOF设计模式之一。简单工厂模式是由一个工厂对象决定创建出哪一种产品 类的实例。简单工厂模式是工厂模式家族中最简单实用的模式，可以理解为是不同工厂模式的一个特殊实现
 *
 *</p>
 * 工厂类
 */
public interface InterfaceCreateFactory {
    InterfaceProduct getActualCreater();
}





























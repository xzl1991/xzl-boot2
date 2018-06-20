package DesignModel.decorateModel;

import DesignModel.decorateModel.decoratorReals.RealDecorate1;
import DesignModel.decorateModel.decoratorReals.RealDecorate2;
import DesignModel.decorateModel.decorators.Decorator;
import DesignModel.decorateModel.impl.First;
import DesignModel.decorateModel.interfaces.MyInterface;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ${xzl} on 2017/9/5.
 *
 * 装饰器
 * <p>
 *
 *
 * </p>
 */
public class TestMain {
    public static void main(String[] args) {
        MyInterface  first = new First();
        first.sayHello();//原来的方法

        RealDecorate1 decorator1 = new RealDecorate1(first);
        decorator1.sayHello();//原来的方法
        decorator1.beforeSay();//新增的方法

        RealDecorate2 decorator2 = new RealDecorate2(first);//装饰城 2
        decorator2.sayHello();//原来的方法
        decorator2.beforeSay();//装饰器 2 新增的方法
        System.out.println("**** 1 装饰城 2 ******");
        decorator2 = new RealDecorate2(decorator1);// 1 装饰城 2
        decorator2.sayHello();//原来的方法
        decorator2.beforeSay();//装饰器 2 新增的方法

        Set  set = new HashSet();
        Person person = new Person("张三",22);
        Person person2 = new Person("李四",33);
        set.add(person);
        set.add(person2);

        person2.setAge(11);
        set.remove(person2);
        System.out.println(set.size());

        set.add(person2);
        System.out.println("新的:"+set.size());
        for (Object item : set){
            System.out.println(((Person)item).getAge()+","+((Person)item).getName());
        }

    }
}



































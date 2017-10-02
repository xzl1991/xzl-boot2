package main.DesignModel.decorateModel.impl;

import main.DesignModel.decorateModel.TestMain;
import main.DesignModel.decorateModel.interfaces.MyInterface;

/**
 * Created by ${xzl} on 2017/9/5.
 */
public class First implements MyInterface {
    @Override
    public void sayHello() {
        System.out.println("第一次hello!");
    }
}

package DesignModel.decorateModel.impl;

import DesignModel.decorateModel.TestMain;
import DesignModel.decorateModel.interfaces.MyInterface;

/**
 * Created by ${xzl} on 2017/9/5.
 */
public class First implements MyInterface {
    @Override
    public void sayHello() {
        System.out.println("第一次hello!");
    }
}

package main.DesignModel.Factory.AbFactory.impl;

import main.DesignModel.Factory.AbFactory.Interface.MyProduct;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class Product2 implements MyProduct {
    @Override
    public void create() {
        System.out.println("自定义产品----2");
    }
}

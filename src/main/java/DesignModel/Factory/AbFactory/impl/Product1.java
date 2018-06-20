package DesignModel.Factory.AbFactory.impl;

import DesignModel.Factory.AbFactory.Interface.MyProduct;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class Product1 implements MyProduct {
    @Override
    public void create() {
        System.out.println("自定义产品----1");
    }
}

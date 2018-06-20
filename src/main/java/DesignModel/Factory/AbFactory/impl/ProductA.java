package DesignModel.Factory.AbFactory.impl;

import DesignModel.Factory.AbFactory.Interface.MyProductA;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class ProductA implements MyProductA
{
    @Override
    public void create() {
        System.out.println("自定义产品----A");
    }
}

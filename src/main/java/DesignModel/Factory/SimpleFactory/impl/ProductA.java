package DesignModel.Factory.SimpleFactory.impl;

import DesignModel.Factory.SimpleFactory.InterfaceProduct;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class ProductA implements InterfaceProduct{
    @Override
    public void create() {
        System.out.println("生成产品----A");
    }
}

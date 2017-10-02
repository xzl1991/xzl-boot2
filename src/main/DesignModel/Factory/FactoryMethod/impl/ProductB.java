package main.DesignModel.Factory.FactoryMethod.impl;


import main.DesignModel.Factory.FactoryMethod.InterfaceProduct;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class ProductB implements InterfaceProduct {
    @Override
    public void create() {
        System.out.println("方法工厂--生成产品----B");
    }
}

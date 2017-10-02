package main.DesignModel.Factory.AbFactory.ActualCreate;

import main.DesignModel.Factory.AbFactory.CreateFactory;
import main.DesignModel.Factory.AbFactory.Interface.MyProduct;
import main.DesignModel.Factory.AbFactory.Interface.MyProductA;
import main.DesignModel.Factory.AbFactory.impl.Product1;
import main.DesignModel.Factory.AbFactory.impl.Product2;
import main.DesignModel.Factory.AbFactory.impl.ProductA;
import main.DesignModel.Factory.AbFactory.impl.ProductB;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class Creater2 implements CreateFactory{
    @Override
    public MyProductA getMyProduct1() {
        return new ProductB();
    }

    @Override
    public MyProduct getMyProduct() {
        return new Product2();
    }
}

package DesignModel.Factory.AbFactory.ActualCreate;

import DesignModel.Factory.AbFactory.CreateFactory;
import DesignModel.Factory.AbFactory.Interface.MyProduct;
import DesignModel.Factory.AbFactory.Interface.MyProductA;
import DesignModel.Factory.AbFactory.impl.Product1;
import DesignModel.Factory.AbFactory.impl.Product2;
import DesignModel.Factory.AbFactory.impl.ProductA;
import DesignModel.Factory.AbFactory.impl.ProductB;

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

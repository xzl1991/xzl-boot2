package DesignModel.Factory.AbFactory;

import DesignModel.Factory.AbFactory.Interface.MyProduct;
import DesignModel.Factory.AbFactory.Interface.MyProductA;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public interface CreateFactory {
    MyProduct getMyProduct();

    MyProductA getMyProduct1();
}

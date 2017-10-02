package main.DesignModel.Factory.AbFactory;

import main.DesignModel.Factory.AbFactory.Interface.MyProduct;
import main.DesignModel.Factory.AbFactory.Interface.MyProductA;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public interface CreateFactory {
    MyProduct getMyProduct();

    MyProductA getMyProduct1();
}

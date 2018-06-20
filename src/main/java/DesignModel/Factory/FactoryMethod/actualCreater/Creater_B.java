package DesignModel.Factory.FactoryMethod.actualCreater;


import DesignModel.Factory.FactoryMethod.InterfaceCreateFactory;
import DesignModel.Factory.FactoryMethod.InterfaceProduct;
import DesignModel.Factory.FactoryMethod.impl.ProductB;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class Creater_B implements InterfaceCreateFactory {
    @Override
    public InterfaceProduct getActualCreater() {
        return new ProductB();
    }
}

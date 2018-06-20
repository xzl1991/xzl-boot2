package DesignModel.Factory.FactoryMethod.actualCreater;


import DesignModel.Factory.FactoryMethod.InterfaceCreateFactory;
import DesignModel.Factory.FactoryMethod.InterfaceProduct;
import DesignModel.Factory.FactoryMethod.impl.ProductA;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class Creater_A implements InterfaceCreateFactory {
    @Override
    public InterfaceProduct getActualCreater() {
        return new ProductA();
    }
}

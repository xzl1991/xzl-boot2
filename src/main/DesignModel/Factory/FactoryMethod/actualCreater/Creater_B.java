package main.DesignModel.Factory.FactoryMethod.actualCreater;


import main.DesignModel.Factory.FactoryMethod.InterfaceCreateFactory;
import main.DesignModel.Factory.FactoryMethod.InterfaceProduct;
import main.DesignModel.Factory.FactoryMethod.impl.ProductB;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class Creater_B implements InterfaceCreateFactory {
    @Override
    public InterfaceProduct getActualCreater() {
        return new ProductB();
    }
}

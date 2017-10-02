package main.DesignModel.Factory.FactoryMethod;

import main.DesignModel.Factory.FactoryMethod.actualCreater.Creater_A;
import main.DesignModel.Factory.FactoryMethod.actualCreater.Creater_B;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class TestMain {
    public static void main(String[] args) {
        InterfaceProduct  product = null;
        InterfaceCreateFactory factory = new Creater_A();
        product = factory.getActualCreater();
        product.create();


        factory = new Creater_B();
        product = factory.getActualCreater();
        product.create();
    }
}

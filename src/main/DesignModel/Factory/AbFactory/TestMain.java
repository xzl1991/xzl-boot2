package main.DesignModel.Factory.AbFactory;

import main.DesignModel.Factory.AbFactory.ActualCreate.Creater1;
import main.DesignModel.Factory.AbFactory.ActualCreate.Creater2;
import main.DesignModel.Factory.AbFactory.Interface.MyProduct;
import main.DesignModel.Factory.AbFactory.Interface.MyProductA;
import main.DesignModel.Factory.AbFactory.impl.Product1;

/**
 * Created by ${xzl} on 2017/8/28.
 */
public class TestMain {
    public static void main(String[] args) {
        CreateFactory  createFactory = null;
        //创建工厂
        createFactory = new Creater1();
        MyProduct product1 = createFactory.getMyProduct();
        MyProductA productA = createFactory.getMyProduct1();
        product1.create();
        productA.create();

        createFactory = new Creater2();
        product1 = createFactory.getMyProduct();
        productA = createFactory.getMyProduct1();
        product1.create();
        productA.create();
    }
}

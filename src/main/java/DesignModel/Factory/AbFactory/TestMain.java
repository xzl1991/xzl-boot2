package DesignModel.Factory.AbFactory;

import DesignModel.Factory.AbFactory.ActualCreate.Creater1;
import DesignModel.Factory.AbFactory.ActualCreate.Creater2;
import DesignModel.Factory.AbFactory.Interface.MyProduct;
import DesignModel.Factory.AbFactory.Interface.MyProductA;
import DesignModel.Factory.AbFactory.impl.Product1;

/**
 * Created by ${xzl} on 2017/8/28.
 *
 * UML 图： https://www.cnblogs.com/qiaoconglovelife/p/5750290.html
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

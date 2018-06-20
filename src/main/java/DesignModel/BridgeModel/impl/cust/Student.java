package DesignModel.BridgeModel.impl.cust;


import DesignModel.BridgeModel.interfaces.CustType;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class Student implements CustType {
    @Override
    public void custType() {
        System.out.println("我是一个小学生---我要出去旅游了~~");
    }
}

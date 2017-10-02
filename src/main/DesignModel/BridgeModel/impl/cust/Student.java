package main.DesignModel.BridgeModel.impl.cust;

import main.DesignModel.BridgeModel.PeopleTrave;
import main.DesignModel.BridgeModel.interfaces.CustType;
import main.DesignModel.BridgeModel.interfaces.TraveType;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class Student implements CustType {
    @Override
    public void custType() {
        System.out.println("我是一个小学生---我要出去旅游了~~");
    }
}

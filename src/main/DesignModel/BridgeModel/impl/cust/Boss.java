package main.DesignModel.BridgeModel.impl.cust;

import main.DesignModel.BridgeModel.PeopleTrave;
import main.DesignModel.BridgeModel.interfaces.CustType;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class Boss implements CustType {
    @Override
    public void custType() {
        System.out.println("我是一个大老板---我要出去旅游了~~");
    }
}

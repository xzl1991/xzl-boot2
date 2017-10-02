package main.DesignModel.BridgeModel.impl.cust;

import main.DesignModel.BridgeModel.PeopleTrave;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class People extends PeopleTrave{
    @Override
    protected void sayHello() {
        System.out.println("开场:hello ");
    }
}

package DesignModel.BridgeModel.impl.vehicle;


import DesignModel.BridgeModel.interfaces.TraveType;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class Car implements TraveType {
    @Override
    public void traveBy() {
        System.out.println("我坐小汽车出行---");
    }
}

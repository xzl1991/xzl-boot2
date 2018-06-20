package DesignModel.BridgeModel.impl.vehicle;


import DesignModel.BridgeModel.interfaces.TraveType;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class AirPlan implements TraveType {
    @Override
    public void traveBy() {
        System.out.println("我坐飞机出行---");
    }
}

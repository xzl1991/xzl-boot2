package DesignModel.strategy.impl;


import DesignModel.strategy.MyInterface;
import DesignModel.strategy.NumRegion;

@NumRegion(max=10)
public class CommonInsert implements MyInterface {
	@Override
	public void insertCode() {
		System.out.println("---普通的---0~10");
	}
}

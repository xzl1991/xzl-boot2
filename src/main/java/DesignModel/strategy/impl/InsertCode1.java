package DesignModel.strategy.impl;

import DesignModel.strategy.MyInterface;
import DesignModel.strategy.NumRegion;

@NumRegion(min=11, max=20)
public class InsertCode1 implements MyInterface {
	@Override
	public void insertCode() {
		System.out.println("---实现类--插入的方法10~20");
	}
}

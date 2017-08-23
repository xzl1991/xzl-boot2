package strategy.impl;

import strategy.MyInterface;
import strategy.NumRegion;

@NumRegion(min=21, max=30)
public class InsertCode2 implements MyInterface {
	@Override
	public void insertCode() {
		System.out.println("---实现类--插入的方法20-30");
	}
}

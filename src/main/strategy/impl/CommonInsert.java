package strategy.impl;


import strategy.MyInterface;
import strategy.NumRegion;

@NumRegion(max=10)
public class CommonInsert implements MyInterface {
	@Override
	public void insertCode() {
		System.out.println("---普通的---0~10");
	}
}

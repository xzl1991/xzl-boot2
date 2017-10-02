package DesignModel.strategy;

import DesignModel.strategy.impl.CommonInsert;

public class MyClass {
	private int methodNum;
    private MyInterface myInterface = new CommonInsert();//每个客户都有一个计算价格的策略，初始都是普通计算，即原价
	public void myMethod(MyInterface myinterface){//client
		System.out.println("myclass里的方法----start");
		myinterface.insertCode();//接口的方法
		System.out.println("myclass里的方法----end");
	}
	public void testFactoy(int methodNum){//clientFactory
		this.methodNum = methodNum;
		myInterface = CodeFactoryFactory.createMyInterface(this);
		myInterface.insertCode();//接口的方法
	}
	public void testImproFactoy(int methodNum){//clientFactory--优化
		this.methodNum = methodNum;
		myInterface = CodeImproveFactory.getInstance().createMyInterface(this);//CodeFactoryFactory.createMyInterface(this);
		myInterface.insertCode();//接口的方法
	}
	
	
	public int getMethodNum() {
		return methodNum;
	}
	public void setMethodNum(int methodNum) {
		this.methodNum = methodNum;
	}
	
}

package DesignModel.strategy;


import DesignModel.strategy.impl.CommonInsert;
import DesignModel.strategy.impl.InsertCode1;
import DesignModel.strategy.impl.InsertCode2;

/**
 * 策略工厂  简单工厂
 * */
public class CodeFactoryFactory {
	private CodeFactoryFactory(){}
	public static MyInterface createMyInterface(MyClass myClass){
		if(myClass.getMethodNum()==1){
			return new InsertCode1();
		}
		if(myClass.getMethodNum()==2){
			return new InsertCode2();
		}
		return new CommonInsert();
	}
}	

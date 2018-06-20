
package DesignModel.strategy.client;

import DesignModel.strategy.MyClass;
import DesignModel.strategy.impl.InsertCode1;
import DesignModel.strategy.impl.InsertCode2;

public class Client {

	public static void main(String[] args) {
		MyClass myClass = new MyClass();
//		myClass.myMethod(null);
		System.out.println("---------------");
		myClass.myMethod(new InsertCode1());
		System.out.println("---------------");
		myClass.myMethod(new InsertCode2());
		
	}

}

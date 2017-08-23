package strategy.client;

import strategy.MyClass;
import strategy.NumRegion;
import strategy.impl.CommonInsert;

public class ClientImproFactory {

	public static void main(String[] args) {
		MyClass myClass = new MyClass();
		myClass.testFactoy(1);
		myClass.testFactoy(2);
		myClass.testFactoy(10);

		System.out.println("***************");
		myClass.testImproFactoy(10);
		myClass.testImproFactoy(20);
	}

}

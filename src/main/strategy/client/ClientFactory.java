package strategy.client;

import strategy.MyClass;
import strategy.NumRegion;
public class ClientFactory {

	public static void main(String[] args) {
		MyClass myClass = new MyClass();
		myClass.testFactoy(1);
		myClass.testFactoy(2);
		myClass.testFactoy(10);
		
	}

}

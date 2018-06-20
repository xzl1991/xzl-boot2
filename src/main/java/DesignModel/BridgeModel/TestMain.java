package DesignModel.BridgeModel;


import DesignModel.BridgeModel.impl.cust.Boss;
import DesignModel.BridgeModel.impl.cust.People;
import DesignModel.BridgeModel.impl.cust.Student;
import DesignModel.BridgeModel.impl.vehicle.AirPlan;
import DesignModel.BridgeModel.impl.vehicle.Bus;

/**
 * Created by ${xzl} on 2017/9/18.
 *
 * <p>
 *     通过对象组合的方式，Bridge 模式把两个角色之间的继承关系改为了耦合的关系，从而使这两者可以从容自若的各自独立的变化，这也是Bridge模式的本意。
 * </p>
 */
public class TestMain {
    public static void main(String[] args) {
        PeopleTrave trave = new People();
        trave.setCustType(new Student());
        trave.setTraveType(new Bus());
        trave.sayHello();
        trave.showCustType();
        trave.traveBy();

        System.out.println("========");
        trave.setCustType(new Boss());
        trave.setTraveType(new AirPlan());
        trave.sayHello();
        trave.showCustType();
        trave.traveBy();

        System.out.println("====任性的小学生====");
        trave.setCustType(new Student());
        trave.setTraveType(new AirPlan());
        trave.sayHello();
        trave.showCustType();
        trave.traveBy();
    }
}

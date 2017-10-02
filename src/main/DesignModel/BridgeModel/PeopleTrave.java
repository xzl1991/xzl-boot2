package main.DesignModel.BridgeModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import main.DesignModel.BridgeModel.interfaces.CustType;
import main.DesignModel.BridgeModel.interfaces.TraveType;

/**
 * Created by ${xzl} on 2017/9/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@AllArgsConstructor
@NoArgsConstructor
public abstract class PeopleTrave {
    private TraveType traveType;
    private CustType custType;
    protected  void traveBy(){
        traveType.traveBy();
    };
    protected  void showCustType(){
        custType.custType();
    };

    protected  abstract void sayHello();//出场词

    public  PeopleTrave(TraveType traveType,CustType custType){
        this.custType = custType;
        this.traveType = traveType;
    }
}

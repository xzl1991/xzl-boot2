package DesignModel.ObserveModel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by ${xzl} on 2017/8/29.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class People {
    String name;
    int cone;
    public  void temp(int reward){
        System.out.println(this.name+": 好的!马上看。打赏"+reward);
        this.cone -= reward;
        System.out.println("剩余金币:"+ this.cone);
    }
}

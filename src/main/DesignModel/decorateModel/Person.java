package main.DesignModel.decorateModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by ${xzl} on 2017/9/6.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Person {
    private  String name;
    private int age;
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }
}

package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by ${xzl} on 2017/7/5.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Columns {
    private  String header;
    private  String dataIndex;
    private  boolean hidden;
    private String getChild(Class classes){
        return  "test ---"+classes;
    }
}

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
public class ViewConfig {
    private  boolean enableTextSelection;
    private  boolean scrollable;
    private  String getRowClass;

    public ViewConfig(){
        this.enableTextSelection = true;
        this.getRowClass = "function(record, rowIndex, rowParams, store){\n" +
                "                        // console.log((rowIndex %2 ==1))\n" +
                "                        if(rowIndex %2 ==1){\n" +
                "                            return 'blue-col-lines';\n" +
                "                        }else{\n" +
                "                            return 'width-col-lines';\n" +
                "                        }\n" +
                "                    }";
    }
}

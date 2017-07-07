package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by ${xzl} on 2017/7/5.
 * <P>生成gridpanel</P>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class Panel {
    private  String id;
    private  String region;
    private ViewConfig viewConfig;
    private Columns columns;
    private  String store;
    private  boolean rootVisible;
    private  boolean columnLines;
    private  boolean animate;

    private  String align;
    private  boolean sortable;
    public  Panel(){
        this.region = "center";
        this.rootVisible = true;
        this.columnLines = true;
        this.animate = true;
        this.sortable = true;
        this.viewConfig = new ViewConfig();
    }
}

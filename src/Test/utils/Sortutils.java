package utils;

import java.util.*;

/**
 * Created by xzl on 2017/12/6.
 *
 * @author xzl
 * @date 2017/12/6  15:59.
 */
public class Sortutils {
    public  static void sortByValue(List<Map.Entry<Object, Integer>> list){
        //按照value排序
//        Collections.sort(list, Comparator(Map.Entry::getValue));
        Collections.sort(list, new Comparator<Map.Entry<Object, Integer>>() {
            public int compare(Map.Entry<Object, Integer> o1, Map.Entry<Object, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
    }
}

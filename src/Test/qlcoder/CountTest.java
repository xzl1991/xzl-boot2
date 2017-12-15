package qlcoder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;
import utils.ExcelUtils;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by xzl on 2017/12/5.
 *
 * @author xuzelong
 * @date 2017/12/5  14:31.
 */
public class CountTest {
    final  static int MAX = 5000;
    public static void main(String[] args) {
        Map<Integer,Integer> map = new TreeMap();
        File file = new File("C:\\Users\\Administrator\\Desktop\\新建文件夹 (3)\\导出模板\\Goods.xlsx");
        JSONArray array = ExcelUtils.readXLSXExcelToArray(file,false);
        JSONObject jsonObject ;
        String[] results ;
        int key = 0;
        boolean first = true;
        int value = 0;
        for (int i = 0; i < array.size(); i++) {
            jsonObject = array.getJSONObject(i);
            results = jsonObject.get("KEY").toString().split(" ");
            for (String string : results){
                if(!StringUtils.isEmpty(string)){
                    if(first){
                        key = Integer.parseInt(string);
                        first = false;
                    }else {
                        value = Integer.parseInt(string);
                        first = true;
                    }
                    map.put(key,value);
                }
            }
        }

        //按照value排序
        List<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//        Collections.sort(list, Comparator(Map.Entry::getValue));
        Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
//                return map.get(o1).compareTo(map.get(o2));
            }
        });
//        Entry<Integer, Integer> e: list
        Map finalValue = new TreeMap();
        int size = list.size();

        StringBuilder sb;
        for (int i=0;i<size;i++){
            sb = new StringBuilder();
            int tempTotal = 0;
            System.out.println(list.get(i).getKey()+":"+list.get(i).getValue());
            for(int j=i;j<size;j++){
//                tempTotal = sum(j,tempTotal,list,sb,size);
                if(checkSum(j,tempTotal,list)){
                    tempTotal = sum(j,tempTotal,list,sb,size);
                }else{
                    finalValue.put(sb.toString(),tempTotal);
                    break;
                }
            }

        }
        List<Entry<String, Integer>> list1 = new ArrayList<>(finalValue.entrySet());
        Collections.sort(list1, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return (Integer)o2.getValue().compareTo(((Integer)o1.getValue()));
//                return map.get(o1).compareTo(map.get(o2));
            }
        });
        for (Entry<String, Integer> e: list1) {
            System.out.println("结果："+  e.getKey()+":"+e.getValue());
        }
    }
    private  static int sum(int i,int tempTotal,List<Entry<Integer, Integer>> list, StringBuilder sb,int size){
        if(checkSum(i,tempTotal,list)){
            tempTotal+=list.get(i).getValue();
            sb.append(list.get(i).getKey()).append("-");
        }else if(checkSum(size,tempTotal,list)){//后面有可以继续加的
            for(int j=i;j<size;j++){
                if(checkSum(j+1,tempTotal,list)){
                    sum(j+1,tempTotal,list,sb,size);
                };
            }
        }
        return  tempTotal;
    }
    private  static boolean checkSum(int index,int tempTotal,List<Entry<Integer, Integer>> list){
        return (MAX-tempTotal)>=list.get(index).getValue();
    }
}

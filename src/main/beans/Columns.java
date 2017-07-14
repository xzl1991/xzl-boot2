package main.beans;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

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
    public static  String getChildByMap(Map<String,String> map){
        StringBuilder stringBuilder = new StringBuilder(200);
        stringBuilder.append(":[{\n");
        for(Map.Entry entry : map.entrySet()){
            stringBuilder.append("'").append(entry.getKey()).append("':'").append(entry.getValue()).append("',\n");
        }
        stringBuilder.append("}]");
        return  stringBuilder.toString();
    }
    public static  String getChildByJson(JSONArray jsonArray){
        StringBuilder stringBuilder = new StringBuilder(200);
        stringBuilder.append(":[\n");
        JSONObject jsonObject = null;
        for(Object object : jsonArray ){
            jsonObject = (JSONObject) object;
            stringBuilder.append("{\n");
            for(String key: jsonObject.keySet()){
                stringBuilder.append("'").append(key).append("':'").append(jsonObject.get(key)).append("',\n");
            }
            stringBuilder.append("},\n");
        }
        stringBuilder.append("]");
        return  stringBuilder.toString();
    }
    private static  String getChildByMap1(Map<String,String> map){
        JSONArray array = new JSONArray();
        JSONObject obj = null;
        for(Map.Entry entry : map.entrySet()){
            obj = new JSONObject();
            obj.put((String) entry.getKey(), entry.getValue());
            array.add(obj);
//            stringBuilder.append("'").append(entry.getKey()).append("':'").append(entry.getValue()).append("'");
        }
        return  array.toString();
    }
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("header","测试");
        map.put("dataIndex","acctNbr");
        JSONArray array = new JSONArray();
        JSONObject objs = null;
        objs = new JSONObject();
        objs.put("header","测试");
        objs.put("dataIndex","acctNbr");
        array.add(objs);
        objs = new JSONObject();
        objs.put("header","姓名");
        objs.put("dataIndex","name");
        array.add(objs);
        System.out.println(getChildByMap(map));
        System.out.println(getChildByJson(array));
    }
}

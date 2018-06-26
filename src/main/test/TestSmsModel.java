package test;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @auther xzl on 10:03 2018/4/17
 */
public class TestSmsModel {
    public static void main(String[] args) {
        String head = "0 0 *";
        String cron = "0 0 3 20-24 4 * ?";
        String cron1 = "0 0 * 20-24 4 * ?";
        System.out.println(cron.startsWith(head));
        System.out.println(cron1.startsWith(head));
//        Map<String,Object>  param = new HashMap<>();
        JSONObject param = new JSONObject();
        Gson gson = new Gson();
        param.put("name","zhang");
        String sms = "{a},sdasd";
        param.put("age", Arrays.asList(sms));
        param.put("ageList", Arrays.asList(sms.split(",")));
        Map<String, Object> res = gson.fromJson(param.toJSONString(), new TypeToken<Map<String, Object>>() {}.getType());
        String[] arg  =  sms.split("\\{");
        for (String a : arg){
            System.out.println(a);
        }
        new Random().nextInt(31);
        for (int i=0;i<20;i++){
            System.out.println("随机端口："+new Random().nextInt(32));
        }
    }
}

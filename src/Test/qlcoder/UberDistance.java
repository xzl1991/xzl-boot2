package qlcoder;

import com.alibaba.fastjson.JSONArray;
import utils.ReadDataFromText;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static utils.DecimalTrans.getPrettyNumber;

/**
 * Created by xzl on 2017/12/6.
 *89*P19+y*1+yP7+yyPP3+P58*4+P48*yP3*9-yP38*+yP3+yP6-yP8-yP3/Pe
 * @author xuzelong@gomeholdings.com
 * @date 2017/12/6  9:43.
 */
public class UberDistance {
    public static void main(String[] args) {
        JSONArray userArray = ReadDataFromText.readText("C:\\Users\\Administrator\\Desktop\\新建文件夹 (3)\\Data\\Car\\person.txt"," ");
        JSONArray carArray = ReadDataFromText.readText("C:\\Users\\Administrator\\Desktop\\新建文件夹 (3)\\Data\\Car\\car.txt"," ");
        Map<Object,String> resultMap = new TreeMap<>();
        String[] user;
        String[] cars;
        double x = 0;
        double y = 0;
        double carX = 0;
        double carY = 0;
        double distanceNew = 0;
        int tempCarNb = 0;
        Map<Integer,Integer> tempUsed = new HashMap<>();
        for(int i=0;i<userArray.size();i++){
            double distance=0;
            user = (String[])userArray.get(i);
            x = Double.parseDouble(user[0]);
            y = Double.parseDouble(user[1]);
            int j=0;
            for( ;j<carArray.size();j++ ){
                if(tempUsed.get(j)!=null){
                    continue;
                }
                cars = (String[])carArray.get(j);
                carX = Double.parseDouble(cars[0]);
                carY = Double.parseDouble(cars[1]);
                if(j==0){
                    distance = Math.sqrt(((Math.pow(Math.abs(x-carX),2))+(Math.pow(2,Math.abs(y-carY)))));
                }
                distanceNew = Math.sqrt(((Math.pow(Math.abs(x-carX),2))+(Math.pow(2,Math.abs(y-carY)))));
//                distance = getPrettyNumber(Math.sqrt(((Math.pow(Math.abs(x-carX),2))+(Math.pow(2,Math.abs(y-carY))))));
                if(distanceNew<distance){
                    tempCarNb = j;
                }
            }
            tempUsed.put(tempCarNb,tempCarNb);
            resultMap.put("P"+(i+1)+"-U"+(tempCarNb+1),getPrettyNumber(distance));
        }
        System.out.println(resultMap.size()+"****************");
        for(Map.Entry<Object,String> entry : resultMap.entrySet()){
            System.out.println( entry.getKey());
        }
    }

    private  static  void swap(double pre,double now){

    }

}

package qlcoder;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther xzl on 10:20 2018/1/18
 * 给定一个英文字符串,请写一段代码找出这个字符串中首先出现三次的那个英文字符
 */
public class StringArgs {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String line;
            while (true) {
                line = scanner.nextLine();
                if(line==null){
                    continue;
                }
                print(line);
            }
        }catch (Exception e){

        }
    }

    public static void print(String name){
        ConcurrentHashMap<Character,Integer>  map = new ConcurrentHashMap();
        char[] chars = name.toCharArray();
        for(char v : chars){
            if((65<=v&&v<=90)||(97<=v && v<=122)){
                if (map.get(v)==null){
                    map.put(v,1);
                    continue;
                }
                map.put(v,map.get(v)+1);
                if((map.get(v)>=3)){
                    System.out.println(v);
                    break;
                }

            }
        }
    }
}


































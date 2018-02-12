package jvm;

import java.util.List;
import java.util.Vector;

/**
 * @auther xzl on 14:43 2018/1/9
 */
public class MarkWordTest {
    public static List<Integer> list = new Vector<>();
    public static void main(String[] args) {
        int count = 0;
        int value = 0;
        long start = System.currentTimeMillis();
        while(count<10000){
            list.add(value);
            value+=2;
            count++;
        }
        System.out.println("vm参数：-XX:+UseBiasedLocking   -client -Xmx10m -Xms10m ");
        System.out.println("时间："+(System.currentTimeMillis()-start));
    }
}

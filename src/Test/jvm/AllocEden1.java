package jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther xzl on 14:11 2018/1/8
 */
public class AllocEden1 {
    public static final  int K = 1024;
    public static final  int M = 1024*1024;

    public static void main(String[] args) {
        Map map = new HashMap<>();
        for(int i=0;i<5*K;i++){
            byte[] b = new byte[K];
            map.put(i,b);
        }
        for(int i=0;i<17;i++){
            for(int j=0;j<270;j++){
                byte[] bytes = new byte[M];
            }
        }
    }
}

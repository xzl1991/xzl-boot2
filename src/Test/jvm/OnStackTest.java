package jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther xzl on 15:17 2018/1/5
 *
 * 逃逸分析
 */
public class OnStackTest {
    private static class  User{
        int id;
        String name;
    }
    public static void alloc(){
        User user = new User();
        user.id = 1;
        user.name = "测试";
    }
    static User user1;
    public static void alloc1(){
        user1 = new User();
        user1.id = 1;
        user1.name = "测试";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i=0;i<10000000;i++){
            alloc();//回收一次
        }
        System.out.println("耗时："+(System.currentTimeMillis()-start));
    }
}

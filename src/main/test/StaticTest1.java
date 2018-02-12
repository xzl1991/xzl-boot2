package test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @auther xzl on 10:00 2018/1/30
 */

public class StaticTest1 {
    static int x, y;

    static {
        int x = 5;
    }

    public static void main(String args[]) {
//        Array.prototype.distinct
        System.out.println("x的值:"+x);
        x--;
        System.out.println("=X--==="+x);
        myMethod();
        System.out.println(x + y + ++x);
    }

    public static void myMethod() {
        y = x++ + ++x;
        y= ++x;
        System.out.println("Y:"+y);
    }
}

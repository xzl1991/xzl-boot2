package DesignModel.decorateModel;

/**
 * Created by ${xzl} on 2017/9/7.
 */
public class TestByte {
    public static void main(String[] args) {
        byte a = 100;
        System.out.println(a+0);
        a /= 2.5 ;
        System.out.println(a);

        System.out.println(1L<<65L);
    }

    public  static  String  getValue(){
        return  "String类型";
    }
//    public  static  int  getValue(){
//        return  12;
//    }
}

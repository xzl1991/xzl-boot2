package jvm;

/**
 * @auther xzl on 17:40 2018/1/10
 */
class  Praent{
    static {
        System.out.println("父类初始化");
    }
    final static  int a = 10;
    static  final  String name = "final 类型的静态值";
}
class  Child extends Praent{
    static  final  String name = "final 类型的静态值";
    static {
        System.out.println("子类初始化");
    }
}

public class ClassLoadTest {
    public static void main(String[] args) {
        System.out.println("000000");
//        System.out.println(Praent.a);
        Praent test = new Child();
    }
}
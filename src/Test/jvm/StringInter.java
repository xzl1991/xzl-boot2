package jvm;

/**
 * @auther xzl on 13:30 2018/1/9
 */
public class StringInter {
    public static void main(String[] args) {
        String a = new String("abc");
        String b = new String("abc");
        System.out.println(a.intern()==b);
        System.out.println(a==b);
        System.out.println("abc"==a.intern());
        System.out.println("abc"==b.intern());
        System.out.println("************");
        System.out.println(a.intern()==b.intern()+"：引用");
        System.out.println(a.intern()+"：a引用");
        System.out.println(b.intern()+"：b引用");

    }
}

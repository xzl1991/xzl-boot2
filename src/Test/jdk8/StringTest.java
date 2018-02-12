package jdk8;

/**
 * @auther xzl on 18:30 2018/1/30
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "abcdefg";
        StringBuffer sb = new StringBuffer(s);
        System.out.println(sb.toString());
    }
}

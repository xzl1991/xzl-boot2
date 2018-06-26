/**
 * @auther xzl on 18:33 2018/3/9
 */
public class TestStringSwap {
    public static void main(String[] args) {
        String zhangsan = "张三";
        String lisi = "李四";
        System.out.println(zhangsan+"====="+lisi);
        swap(zhangsan,lisi);
        System.out.println(zhangsan+"==交换后==="+lisi);
    }
    static void swap(String name,String name1){
        String temp = null;
        temp = name;
        name = name1;
        name1 = temp;
        System.out.println(name+"::"+name1);
    }
}

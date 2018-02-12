package jvm;

/**
 * @auther xzl on 17:26 2018/1/5
 */
public class NewSizeDemo {
    public static void main(String[] args) {
        //查看新生代分配
        /**
         *  设置xmn的时候出现个错误，
           当 xmn 1m 时，年轻代统计错误，甚至出现负数。。。。。。
         *
         * 2.不能 只通过 调比例修改from to eden 关系： 这样修改无效，只有先修改新生代 比例才会生效！
         * */
        byte[] b = null;
        for(int i=0;i<10;i++)
            b = new byte[1*1024*1024];
        System.out.println("最大内存："+Runtime.getRuntime().freeMemory());
        System.out.println("可以内存："+Runtime.getRuntime().maxMemory());
        System.out.println("可以内存："+Runtime.getRuntime().totalMemory());
    }
}

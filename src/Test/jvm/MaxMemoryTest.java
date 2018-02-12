package jvm;

/**
 * @auther xzl on 10:23 2018/1/5
 */
public class MaxMemoryTest {
    public static void main(String[] args) {
        System.out.println("分配的最大内存为: "+ Runtime.getRuntime().maxMemory()/1000/1000+"M");
    }
}

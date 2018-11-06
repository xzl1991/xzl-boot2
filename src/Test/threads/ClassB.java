package threads;

/**
 * @auther xzl on 18:04 2018/3/13
 */
public class ClassB implements Runnable{
    public void  testB(){
        System.out.println("方法B");
    }
    public ClassB(){
        System.out.println("B=====实例化");
    }
    @Override
    public void run() {
        System.out.println("B-----run");
    }
}

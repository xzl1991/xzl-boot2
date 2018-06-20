package threads.runnable;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class DesExample implements Runnable{
    Data data;
    public DesExample(Data data){
        this.data = data;
    }
    @Override
    public void run() {
        data.dec();
        System.out.println("执行-方法");
    }
}

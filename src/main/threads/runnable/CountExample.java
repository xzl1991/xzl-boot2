package main.threads.runnable;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class CountExample implements Runnable{
    Data data;
    public CountExample(Data data){
        this.data = data;
    }
    @Override
    public void run() {
        data.add();
        System.out.println("执行+方法");
    }
}

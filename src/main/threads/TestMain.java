package main.threads;

import main.threads.runnable.CountExample;
import main.threads.runnable.Data;
import main.threads.runnable.DesExample;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        Data data = new Data(10);
        CountExample example = new CountExample(data);
        DesExample desExample = new DesExample(data);
        new Thread(example,"+操作现程0").start();
        new Thread(new CountExample(data),"+操作现程1").start();
        new Thread(desExample,"-操作现程2").start();
        new Thread(new DesExample(data),"-操作现程3").start();
        Thread.sleep(1000);
        System.out.println("最终结果:"+data.getValue());
    }
}

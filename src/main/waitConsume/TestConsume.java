package main.waitConsume;

import main.waitConsume.Producer.Consumer;
import main.waitConsume.Producer.Producer;
import main.waitConsume.SharedStack;

/**
 * Created by ${xzl} on 2017/9/25.
 * <p>生产消费者模式----Lock   Condition---实现</p>
 */
public class TestConsume   {
    public static void main(String[] args) {
       SharedStack sharedStack = new SharedStack(10);
        Consumer consumer = new Consumer(sharedStack);
        Producer producer = new Producer(sharedStack);
        new Thread(consumer,"   消费者开始消费").start();
        new Thread(producer,"   开始干活...").start();
    }
}

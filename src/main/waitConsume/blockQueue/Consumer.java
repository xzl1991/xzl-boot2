package main.waitConsume.blockQueue;

/**
 * Created by ${xzl} on 2017/9/30.
 */
public class Consumer {
    BlockQueueTest test;
    public Consumer(BlockQueueTest test){
        this.test = test;
    }
    public  void cousume(int num) throws InterruptedException {
        test.cousume(num);
    }
}

//package waitConsume.blockQueue;
package main.waitConsume.blockQueue;

/**
 * Created by ${xzl} on 2017/9/30.
 */
public class Creter {
    BlockQueueTest test;
    public Creter(main.waitConsume.blockQueue.BlockQueueTest test){
        this.test = test;
    }
    public  void create(int num){
        test.create(num);
    }
}

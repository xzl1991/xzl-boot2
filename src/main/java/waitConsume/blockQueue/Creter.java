//package waitConsume.blockQueue;
package waitConsume.blockQueue;

/**
 * Created by ${xzl} on 2017/9/30.
 */
public class Creter {
    BlockQueueTest test;
    public Creter(BlockQueueTest test){
        this.test = test;
    }
    public  void create(int num){
        test.create(num);
    }
}

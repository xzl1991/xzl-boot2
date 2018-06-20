package waitConsume.blockQueue;

import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ${xzl} on 2017/9/30.
 * LinkedBlockingQueue 模拟生产消费
 */
public class BlockQueueTest {
    // 仓库最大存储量
    private final int MAX_SIZE = 10;
    //共享载体
    private static  volatile LinkedBlockingQueue<String>  linkedBlockingQueue = new LinkedBlockingQueue(10);
    //生产产品
    public void create(int num){
        if(linkedBlockingQueue.size()>=MAX_SIZE){
            System.out.println("仓库满了---等待消费");
        }
        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i)
        {
            try
            {
                // 放入产品，自动阻塞
                linkedBlockingQueue.put("id：【 "+i+" 】");
                System.out.println("------正在生产");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            System.out.println("【现仓储量为】:" + linkedBlockingQueue.size());
        }

    }


    //消费
    public void cousume(int num) throws InterruptedException {
        if(linkedBlockingQueue.size()==0){
            System.out.println("  当前货不够了---等待生产");
        }
        for(int i=0;i<num;i++){
            // 消费产品，自动阻塞
            System.out.println("------正在消费："+linkedBlockingQueue.take());
        }
        System.out.println("【现仓储量为】:" + linkedBlockingQueue.size());
    }

    private class Provide implements Runnable{
        private LinkedBlockingQueue<String> linkedBlockingQueue;
        public Provide(LinkedBlockingQueue<String> dataQueue){
            this.linkedBlockingQueue = dataQueue;
        }
        @Override
        public void run() {
            try {
             while(true){
                 if(linkedBlockingQueue.size()>=MAX_SIZE){
                     System.out.println("----boom----满了放不下了！！");
                 }
                 System.out.println("生产中---【现仓储量为】:" + linkedBlockingQueue.size());
                 linkedBlockingQueue.put("产品号 :"+ UUID.randomUUID());
                 Thread.sleep(1500);
             }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private  class Consume implements Runnable{
        private LinkedBlockingQueue<String> linkedBlockingQueue;
        public Consume(LinkedBlockingQueue<String> dataQueue){
            this.linkedBlockingQueue = dataQueue;
        }
        @Override
        public void run() {
            try {
                while (true){//必须
                    if(linkedBlockingQueue.isEmpty()){
                        System.out.println("----仓库空了！！");
                    }
                    System.out.println("消费中---【现仓储量为】:" + linkedBlockingQueue.size());
                    System.out.println("正在消费："+linkedBlockingQueue.take());
                    Thread.sleep(3500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Consume consume = new BlockQueueTest().new Consume(linkedBlockingQueue);
        Provide provide = new BlockQueueTest().new Provide(linkedBlockingQueue);
        new Thread(consume,"开始消费").start();
        new Thread(provide,"开始生产").start();
//        BlockQueueTest test  = new BlockQueueTest();
//        Creter creter = new Creter(test);
//        Consumer consumer = new Consumer(test);
////        test.cousume(10);
//        test.create(3);
//        test.cousume(10);
//        test.create(20);
    }
}

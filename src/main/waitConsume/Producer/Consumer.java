package main.waitConsume.Producer;

import main.waitConsume.SharedStack;

/**
 * Created by ${xzl} on 2017/9/25.
 */
public class Consumer implements Runnable{
    private SharedStack sharedStack;
    public Consumer(SharedStack sharedStack){
        this.sharedStack = sharedStack;
    }
    @Override
    public void run() {
            try {
//                for(int i=0;i<100;i++){
//
//                }
                while (true){
                    System.out.println("消费---:"+sharedStack.pop());
                    //每一个字符线程就休眠一下
                    Thread.sleep((int) (Math.random() * 500));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}

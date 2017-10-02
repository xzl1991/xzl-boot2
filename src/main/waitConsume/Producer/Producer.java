package main.waitConsume.Producer;

import main.waitConsume.SharedStack;

/**
 * Created by ${xzl} on 2017/9/25.
 */
public class Producer implements Runnable{
    private SharedStack sharedStack;
    public  Producer(SharedStack sharedStack){
        this.sharedStack = sharedStack;
    }
    @Override
    public void run() {
            try {
                char ch;
//                for(int i=0;i<10000000;i++){
//                    System.out.println("开始生产-----");
//                    ch = (char) (Math.random() * 26 + 'A');
//                    sharedStack.push(ch);
//                }
                while (true){
                    System.out.println("开始生产-----");
                    ch = (char) (Math.random() * 26 + 'A');
                    sharedStack.push(ch);
                    //每一个字符线程就休眠一下
                    Thread.sleep((int) (Math.random() * 1000));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}

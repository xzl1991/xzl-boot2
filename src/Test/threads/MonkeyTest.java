package threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther xzl on 17:24 2018/1/17
 */
public class MonkeyTest {

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(900);
        MonkeyA monkeyA = new MonkeyA(integer);
        MonkeyB monkeyB = new MonkeyB(integer);
        new Thread(monkeyB,"猴子B:").start();
        new Thread(monkeyA,"猴子A:").start();
    }

}
class  MonkeyA implements Runnable{
    AtomicInteger integer;
    int time = 0;
    public MonkeyA(AtomicInteger integer){
        this.integer = integer;
    }
    @Override
    public void run() {
        while (integer.intValue()>=3){
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            integer.addAndGet(-3);
            time++;
            System.out.println(Thread.currentThread().getName()+"拿：3个.拿了"+time+"次"+",当前："+integer.intValue());
        }
        System.out.println("不够3个了~~~~结束了，总共拿了:"+time+" 次");
    }
}
class  MonkeyB implements Runnable{
    AtomicInteger integer;
    int time = 0;
    public MonkeyB(AtomicInteger integer){
        this.integer = integer;
    }
    @Override
    public void run() {
        while (integer.intValue()>=2){
            integer.addAndGet(-2);
            time++;
            System.out.println(Thread.currentThread().getName()+"拿：2个.拿了"+time+"次"+",当前："+integer.intValue());
        }
        System.out.println("不够2个了~~~~结束了，总共拿了:"+time+" 次");
    }
}

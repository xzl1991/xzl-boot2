package threads.volatileTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther xzl on 16:01 2018/3/22
 */
public class ShareModel {
    volatile int num = 100;//    int num = 100; 这里和不加效果一样。因为使用了lock
    int  sign = 0;
    private Lock lock = new ReentrantLock();
    private Condition conditionMth1 = lock.newCondition();
    private Condition conditionMth2 = lock.newCondition();
    public void method1(){
        lock.lock();
        System.out.println("我是:"+Thread.currentThread().getName()+",num1："+num);
        try {
            while (sign==0){
                System.out.println(Thread.currentThread().getName()+"--waiting");
                conditionMth1.await();
            }
            System.out.println(Thread.currentThread().getName()+"---被唤醒,===num:"+num);
//            wait = true;
            sign = 0;
            conditionMth2.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void method2(){
        lock.lock();
        System.out.println("我是:"+Thread.currentThread().getName()+",num2："+num);
        try {
                while (sign==1){
                    System.out.println(Thread.currentThread().getName()+"--waiting");
                    conditionMth2.await();
                }
            num = 101;
            System.out.println(Thread.currentThread().getName()+"---执行,===num2:"+num);
            sign = 1;
            System.out.println("BBBBBB--:"+sign);
            conditionMth1.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
//        wait = false;
        }
}

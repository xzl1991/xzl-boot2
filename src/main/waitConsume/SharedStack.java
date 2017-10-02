package main.waitConsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ${xzl} on 2017/9/25.
 * 共享栈----
 */
public class SharedStack {
    private final int DEFAULT_SIZE = 10;//默认大小10
    private volatile int index = 0;
    private volatile int size = 0;
    private Lock lock = new ReentrantLock();
    private Condition pushCondition = lock.newCondition();
    private Condition popCondition = lock.newCondition();
    //共享区域
    private char[] data;
    public SharedStack(){
        this.size = DEFAULT_SIZE;
        data = new char[size];
    }
    public SharedStack(int size){
        this.size = size;
        data = new char[size];
    }
    /**
     * 生产数据
     * @param c
     */
    public  void push(char c){
        lock.lock();
        try {
            while(index==size){
                System.out.println("生产数据满了-----请等待消费");
                pushCondition.await();
            }
            System.out.println("  ---可以消费----");
            data[index] = c;
            index++;
            popCondition.signalAll();//唤醒等待线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    /**
     * 消费数据
     * @return
     */
    public  char pop(){
        lock.lock();
        char ch;
        try {
            while(index==0){
                System.err.println("数据空了-----请等待生产");
                popCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            pushCondition.signalAll();//唤醒等待线程
            lock.unlock();
        }
        index--;
        ch =  data[index];
        return ch;
    }
}

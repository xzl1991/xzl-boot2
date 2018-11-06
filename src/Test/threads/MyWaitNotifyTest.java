package threads;

/**
 * @auther xzl on 13:29 2018/3/14
 */
public class MyWaitNotifyTest implements Runnable{
    private MyWaitNotify  notify;
    public MyWaitNotifyTest(MyWaitNotify  notify){
        this.notify = notify;
    }
    @Override
    public void run() {
        System.out.println("开始执行====="+Thread.currentThread().getName());
        if (Thread.currentThread().getName().indexOf("Thread0")>-1){
            notify.doWait();
            return;
        }
        notify.doNotify();
    }

    public static void main(String[] args) {
        MyWaitNotify  notify = new MyWaitNotify();
        MyWaitNotify  notify1 = new MyWaitNotify();
        MyWaitNotifyTest test = new MyWaitNotifyTest(notify);
        MyWaitNotifyTest test1 = new MyWaitNotifyTest(notify1);
        new Thread(test,"Thread0").start();
        new Thread(test1,"Thread1").start();
    }
}

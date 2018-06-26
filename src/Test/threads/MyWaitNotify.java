package threads;

/**
 * @auther xzl on 11:31 2018/3/14
 */
public class MyWaitNotify {
    String myMonitorObject = "";
    boolean wasSignalled = false;
    public void doWait(){
        synchronized(myMonitorObject){
            System.out.println("等待----------"+Thread.currentThread().getName());
            while(!wasSignalled){
                try{
                    System.out.println("进入循环----------"+Thread.currentThread().getName());
                    myMonitorObject.wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify(){
        synchronized(myMonitorObject){
            wasSignalled = true;
            myMonitorObject.notify();
            System.out.println("唤醒开始----------"+Thread.currentThread().getName());
        }
    }
}

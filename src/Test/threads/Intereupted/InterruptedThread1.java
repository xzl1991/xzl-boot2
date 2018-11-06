package threads.Intereupted;

import java.util.concurrent.TimeUnit;

/**
 * @auther xzl on 19:18 2018/5/8
 */
public class InterruptedThread1 implements Runnable{
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(301);
            while (true){
                System.out.println(" ==========方法执行~~~~");
            }
        } catch (java.lang.InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread interThread = new Thread(new InterruptedThread1());
        interThread.start();
//        TimeUnit.SECONDS.sleep(2);
//        interThread.interrupt();

        System.out.println("InterruptedThread interrupted is " + interThread.isInterrupted());
        TimeUnit.SECONDS.sleep(20);
        interThread.interrupt();
        System.out.println("=============== " + interThread.isInterrupted());
    }
}

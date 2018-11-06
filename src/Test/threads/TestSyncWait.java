package threads;

/**
 * @auther xzl on 14:18 2018/3/6
 */
public class TestSyncWait implements Runnable{
    int b = 100;
    public static void main(String[] args) throws InterruptedException {
        TestSyncWait wait = new TestSyncWait();
        new Thread(wait).start();
        wait.m2();
        System.out.println("主线程 b ："+wait.b);
    }
    synchronized void m1() throws InterruptedException {
        b = 1000;   Thread.sleep(1000);
        System.out.println("m1==="+b);
    }
    synchronized void m2() throws InterruptedException {
        Thread.sleep(250); b = 2000;
        System.out.println("m2-----"+b);
    }
    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

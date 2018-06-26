package threads;

/**
 * @auther xzl on 14:18 2018/3/6
 */
public class TestSyncWait1 implements Runnable{
    int b = 100;
    public static void main(String[] args) throws InterruptedException {
        TestSyncWait1 wait = new TestSyncWait1();
        new Thread(wait).start();
        wait.m2();
        System.out.println("主线程 b ："+wait.b);
    }
    void m1() throws InterruptedException {
        synchronized(this){
            b = 1000;   Thread.sleep(1000);
            System.out.println("m1==="+b);
        }
    }
    void m2() throws InterruptedException {
        synchronized(this){
            Thread.sleep(250); b = 2000;
            System.out.println("m2-----"+b);
        }
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

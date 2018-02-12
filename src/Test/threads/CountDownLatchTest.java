package threads;

/**
 * @auther xzl on 15:38 2018/1/29
 */
import java.util.concurrent.CountDownLatch;
public class  CountDownLatchTest{
    public static void main(String[] args) throws InterruptedException {
        int number = 3;
        CountDownLatch latch =  new CountDownLatch(number);

        for(int i=0;i<3;i++){
            ThreadDemo demo = new ThreadDemo(latch,i);
            demo.start();
            Thread.sleep(1000);
            System.out.println(i);
        }
        latch.await();
        System.out.println("Check it Out");
    }
}

class ThreadDemo extends Thread{

    private CountDownLatch latch;
    private int i;
    public ThreadDemo(CountDownLatch latch,int i){
        this.latch = latch;
        this.i = i;
    }

    public void run(){
        try {
            System.out.println("开始执行");
            Thread.sleep(1);
            if (i==2){
                System.out.println("最后一个很慢");
                Thread.sleep(5000);
            }
            System.out.println(Thread.currentThread().getName()+"执行完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}

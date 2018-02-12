package threads;

/**
 * @auther xzl on 12:42 2018/1/26
 */
public class ThreadTest extends Thread{
    public static void main(String[] args) throws InterruptedException {
        String name = "中国";
        byte[] bytes = name.getBytes();
        for (int i=0;i<bytes.length;i++){
            System.out.println(bytes[i]);
        }
        Thread thread = new ThreadTest();
        thread.start();
        Thread.sleep(100);
        System.out.println("第二次开始:");
        thread.start();
    }
    public void run(){
//        for(int x=0;x<60;x++)
            System.out.println(Thread.currentThread()+"子线程运行");
    }

}

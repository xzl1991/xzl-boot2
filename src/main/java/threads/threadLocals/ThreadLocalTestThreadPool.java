package threads.threadLocals;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @auther xzl on 18:24 2018/6/22
 */
public class ThreadLocalTestThreadPool {
//    static final ThreadLocal<String> threadParam = new ThreadLocal<>();
    static final InheritableThreadLocal<String> threadParam = new InheritableThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {
        Integer i = new Integer(10);
        i.hashCode();
        ExecutorService execService = Executors.newFixedThreadPool(3);
        //死循环几次才能看出效果
        while (true) {
            Thread t = new Thread(()->{
                threadParam.set("abc");
                System.out.println("线程1:" + threadParam.get());
                //如果不调用remove,将引发问题
//                    threadParam.remove();
            });
            execService.execute(t);
            TimeUnit.SECONDS.sleep(1);
            Thread t2 = new Thread(()-> {
                System.out.println("线程2:" + threadParam.get());
            });
            execService.execute(t2);
        }
    }
}

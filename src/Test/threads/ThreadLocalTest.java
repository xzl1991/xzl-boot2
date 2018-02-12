package threads;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther xzl on 10:17 2018/1/29
 */
public class ThreadLocalTest {
    static class Resource{
        private    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    }
    static class Value{
        public static String getThreadName() {
            return ((String) Resource.threadLocal.get());
        }
        public static void setThreadName(String name) {
            Resource.threadLocal.set(name);
        }
    }
    public static void main(String[] args) {
        final Value value = new Value();
        //方法1
        for(int i = 0 ; i < 15 ; i ++) {
            final String resouce1 = "线程-" + i;
            final String resouce2 = " value = (" + i + ")";
            new Thread(resouce2) {
                public void run() {
                    try {
                        value.setThreadName(resouce1);
                        System.out.println(Thread.currentThread().getName()+"----"+value.getThreadName());
//                    b.display();
                    }finally {
//                        ThreadLocalTest1.ResourceClass.RESOURCE_1.remove();
//                        ThreadLocalTest1.ResourceClass.RESOURCE_2.remove();
                    }
                }
            }.start();
        }
        //方法2 -- 未完成
//        new Thread(new TestRun(value,"线程1"),"线程1").start();
//        new Thread(new TestRun(value,"线程2"),"线程2").start();
//        new Thread(new TestRun(value,"线程3"),"线程3").start();
    }
}



class  TestRun implements Runnable{
    ThreadLocalTest.Value value;
    public TestRun(ThreadLocalTest.Value value,String name){
        this.value = value;
        value.setThreadName(name);
    }
    @Override
    public void run() {
        for (int i=0;i<3;i++)
            System.out.println(Thread.currentThread().getName()+"当前线程名称:"+value.getThreadName());
    }
}

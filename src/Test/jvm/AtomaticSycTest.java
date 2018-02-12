package jvm;

        import java.util.concurrent.CountDownLatch;
        import java.util.concurrent.Executor;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        import java.util.concurrent.atomic.AtomicLong;

/**
 * @auther xzl on 10:27 2018/1/
 * @description 比较 原子操作 和 同步性格
 */
public class AtomaticSycTest {
    private static  final  int MAX_THREAD = 3;//最大线程数
    private static  final  int TASK_COUNT = 3;//任务数
    private static  final  long TARGET = 40000000L;//期望值
    private AtomicLong atomicLong = new AtomicLong(0L);//无锁原子操作
    private  long count = 0L;
    private  static CountDownLatch sycCountDownLatch = new CountDownLatch(TASK_COUNT);
    private  static CountDownLatch atoCountDownLatch = new CountDownLatch(TASK_COUNT);

    private synchronized long getCount(){
        return count;
    }
    private synchronized long incSyn(){
        return count++;
    }

    private class SyncCount implements Runnable{
        private  AtomaticSycTest  atomaticSycTest;
        private long startTime ;
        public SyncCount(AtomaticSycTest atomaticSycTest,long startTime){
            this.atomaticSycTest = atomaticSycTest;
            this.startTime = startTime;
        }
        @Override
        public void run() {
            long v = atomaticSycTest.getCount();
            while(v<TARGET){
                v = atomaticSycTest.incSyn();
            }
            System.out.println("同步方法耗时:"+(System.currentTimeMillis()-startTime) +" 当前值："+v);
            sycCountDownLatch.countDown();
        }
    }
    private class AtomicCount implements Runnable{
        private long startTime ;
        public AtomicCount(long startTime){
            this.startTime = startTime;
        }
        @Override
        public void run() {
            long v = atomicLong.get();
            while(v<TARGET){
                v = atomicLong.incrementAndGet();
            }
            System.out.println("原子操作耗时:"+(System.currentTimeMillis()-startTime) +" 当前值："+v);
            atoCountDownLatch.countDown();
        }
    }
    //外部调用
    public void testSyn() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
        long start = System.currentTimeMillis();
        SyncCount syncCount = new SyncCount(this,start);
        for(int i=0;i<TASK_COUNT;i++){
            executorService.submit(syncCount);
        }
        sycCountDownLatch.await();
        executorService.shutdown();
    }

    public  void testAtomic() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
        long start = System.currentTimeMillis();
        AtomicCount atomicCount = new AtomicCount(start);
        for(int i=0;i<TASK_COUNT;i++){
            executorService.submit(atomicCount);
        }
        atoCountDownLatch.await();
        executorService.shutdown();

    }

    public static void main(String[] args) throws InterruptedException {
        AtomaticSycTest sycTest = new AtomaticSycTest();
        sycTest.testSyn();
        sycTest.testAtomic();
    }
}

























































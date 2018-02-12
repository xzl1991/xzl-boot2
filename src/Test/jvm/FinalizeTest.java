package jvm;

/**
 * @auther xzl on 16:46 2018/1/8
 */
public class FinalizeTest {
    private static class Finalize{
        private  byte[] bytes = new byte[512];

        @Override
        protected void finalize() throws Throwable {
            System.out.println("调用了finalize ："+Thread.currentThread().getId());
            Thread.sleep(1000);
//            super.finalize();
        }

        public static void main(String[] args) {
            long st = System.currentTimeMillis();
            for (int i=0;i<50000;i++){
                Finalize f = new Finalize();
            }
            System.out.println((System.currentTimeMillis()-st));
        }
    }
}

package jvm;

import java.util.HashMap;

/**
 * @auther xzl on 10:09 2018/1/8
 */
public class StopWordTest {
    public static void main(String[] args) {
        PrintThread printThread = new PrintThread();
        MyThread myThread = new MyThread();

        Thread print=new Thread(printThread);
        Thread my=new Thread(myThread);
        print.start();
        my.start();
    }

    public  static class  PrintThread implements  Runnable{
        private  static  final  long start = System.currentTimeMillis();
        @Override
        public void run() {
            try {
                while(true){
                    long time =System.currentTimeMillis() - start;
                    System.out.println(time/1000+"===>" + time%1000);
                    Thread.sleep(100);
                }
            }catch (Exception e){

            }
        }
    }

    public  static class  MyThread implements  Runnable{
        HashMap map = new HashMap();
        @Override
        public void run() {
            try {
                while(true){
                    if(map.size()*512/1024/1024>40){
                        map.clear();
                        System.out.println("清除map------");
                    }
                    byte[] b1 ;
                    for(int i=0;i<100;i++){
                        b1 = new byte[512];
                        map.put(System.nanoTime(),b1);
                    }
                    Thread.sleep(1);
                }
            }catch (Exception e){

            }
        }
    }

}




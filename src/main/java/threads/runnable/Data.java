package threads.runnable;

/**
 * Created by ${xzl} on 2017/9/18.
 */
public class Data {
    volatile  int total= 0;
    public Data(int total){
        this.total = total;
    }
    synchronized void add(){
        total++;
        System.out.println(Thread.currentThread().getName()+":执行+操作："+(total));
    }
    synchronized void  dec(){
        total--;
        System.out.println(Thread.currentThread().getName()+":执行-操作："+(total));
    }

    public int getValue(){
//        System.out.println("最终结果:"+this.total);
        return this.total;
    }
}

package threads;

/**
 * @auther xzl on 18:04 2018/3/13
 */
public class ClassA implements Runnable{
    private ClassB classB;

    public ClassA(ClassB classB) {
        this.classB =  classB;
    }
    public ClassA() {
//        this.classB =  classB;
    }

    public void  testClassSyn(){
        synchronized (ClassB.class){
            System.out.println("锁住了classB");
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    private  void test(){
        synchronized(classB){
            try {
                classB.wait(100);
                System.out.println("a的方法");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
//        ClassB classB = new ClassB();
//        ClassA classA = new ClassA(classB);
//        classA.test();
        new Thread(new ClassA()).start();
        ClassB classB = new ClassB();
        new Thread(classB).start();
        classB.testB();
    }

    @Override
    public void run() {
        System.out.println("A-----run");
        testClassSyn();
    }
}

package jvm;

/**
 * @auther xzl on 13:57 2018/1/5
 */
public class LocalVarGc {
    public void localVar1(){
        //未回收
        byte[] a = new byte[6*1024*1024];
        System.gc();
    }
    public void localVar2(){
        //回收
        byte[] a = new byte[6*1024*1024];
        a = null;
        System.gc();
    }

    public void localVar3(){
        //未回收
        {
            byte[] a = new byte[6*1024*1024];
        }
        System.gc();
    }

    public void localVar4(){
        {
            byte[] a = new byte[6*1024*1024];
        }
        int c = 10; //槽位复用
        //回收
        System.gc();
    }
    public void localVar5(){
        localVar1();
        //结束后栈帧被销毁
        //回收
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGc localVarGc = new LocalVarGc();
        System.out.println("方法1 ================");
        localVarGc.localVar1();
        System.out.println("方法2 ================");
        localVarGc.localVar2();
        System.out.println("方法3 ================");
        localVarGc.localVar3();
        System.out.println("方法4 ================");
        localVarGc.localVar4();
        System.out.println("方法5 ================");
        localVarGc.localVar5();
        System.out.println(" ================");

    }
}

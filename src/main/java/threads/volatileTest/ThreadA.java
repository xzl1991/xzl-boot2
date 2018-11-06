package threads.volatileTest;

import waitConsume.SharedStack;

/**
 * @auther xzl on 17:45 2018/3/22
 */
public class ThreadA implements Runnable{
    ShareModel shareModel;
    SharedStack sharedStack;
    public ThreadA(ShareModel shareModel){
        this.shareModel = shareModel;
    }
    public ThreadA(SharedStack sharedStack){
        this.sharedStack = sharedStack;
    }
    @Override
    public void run() {
//        sharedStack.pop();
//        while (true){
            shareModel.method1();
//        }
    }
}

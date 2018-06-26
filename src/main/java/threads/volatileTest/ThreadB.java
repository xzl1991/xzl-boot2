package threads.volatileTest;

import waitConsume.SharedStack;

/**
 * @auther xzl on 17:45 2018/3/22
 */
public class ThreadB  implements Runnable{
    ShareModel shareModel;
    SharedStack sharedStack;
    public ThreadB(ShareModel shareModel){
        this.shareModel = shareModel;
    }

    public ThreadB(SharedStack sharedStack){
        this.sharedStack = sharedStack;
    }
    @Override
    public void run() {
//        sharedStack.push('c');
//        while (true){
            shareModel.method2();
//        }
    }
}

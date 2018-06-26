package threads.volatileTest;

import waitConsume.SharedStack;

/**
 * @auther xzl on 16:12 2018/3/22
 */
public class TestVolatile {
    public static void main(String[] args) {
        ShareModel model = new ShareModel();
        new Thread(new ThreadA(model),"线程A").start();
        new Thread(new ThreadB(model),"线程B").start();
    }
}

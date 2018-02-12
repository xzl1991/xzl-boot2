package systemConcurrent;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @auther xzl on 15:05 2018/2/7
 * 限流:counter  计数器
 */
public class LimitStream {
    //服务访问次数，可以放在Redis中，实现分布式系统的访问计数
    Long counter = 0L;
    //使用LinkedList来记录滑动窗口的10个格子。
    LinkedList<Long> ll = new LinkedList<Long>();
    private void check(){
        while (true){
            try {
                ll.addLast(counter);
                if (ll.size()>10){
                    ll.removeFirst();
                }
                //比较最后一个和第一个，两者相差一秒
                if ((ll.peekLast() - ll.peekFirst()) > 100)
                {
                    //To limit rate
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void getRateLimter(){
        /**
         * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以210的消息。
         * 返回的RateLimiter对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
         * 设置缓冲时间为3秒
         */
        RateLimiter r = RateLimiter.create(2,3, TimeUnit.SECONDS);

        while (true) {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
        }
    }
    public void getByBucket(){
        /**
         * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以2个的消息。
         * 返回的RateLimiter对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
         */
        RateLimiter limiter =  RateLimiter.create(2);
        while (true){
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            System.out.println(limiter.acquire());
        }

    }
    public static void main(String[] args) {
        RateLimiter s = null;
    }
}

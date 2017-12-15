package main.nio.discardServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Date;

/**
 * Created by ${xzl} on 2017/10/9.
 */
public class TimeClientHandle extends ChannelHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg; // (1)在TCP/IP中，NETTY会把读到的数据放到ByteBuf的数据结构中。
        try {
            //这样看起来非常简单，并且和服务端的那个例子的代码也相差不多。然而，处理器有时候会因为抛出IndexOutOfBoundsException而拒绝工作。
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
    }
    /**
     * 在基于流的传输里比如TCP/IP，接收到的数据会先被存储到一个socket接收缓冲里。不幸的是，基于流的传输并不是一个数据包队列，而是一         个字节队列。即使你发送了2个独立的数据包，操作系统也不会作为2个消息处理而仅仅是作为一连串的字节而言。因此这是不能保证你远程         写入的数据就会准确地读取
     * */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

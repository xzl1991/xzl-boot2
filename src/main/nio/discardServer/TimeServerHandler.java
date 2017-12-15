package main.nio.discardServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by ${xzl} on 2017/10/9.
 * @author Administrator
 */
//TIME服务(时间协议的服务)
public class TimeServerHandler extends ChannelHandlerAdapter{
    public  void channelActive(final ChannelHandlerContext ctx){
        final ByteBuf  time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));

        final ChannelFuture  future = ctx.writeAndFlush(time);
//        future.addListener(ChannelFutureListener.CLOSE); 同下
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                assert future == future;
                ctx.close();
            }
        });
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
            cause.printStackTrace();
            ctx.close();
    }
}

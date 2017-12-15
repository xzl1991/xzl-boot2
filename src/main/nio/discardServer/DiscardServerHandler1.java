package main.nio.discardServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by ${xzl} on 2017/10/9.
 */

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler1 extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ECHO服务（响应式协议）
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}

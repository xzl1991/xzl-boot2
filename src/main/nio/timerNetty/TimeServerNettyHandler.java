package main.nio.timerNetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.UnsupportedEncodingException;

/**
 * Created by ${xzl} on 2017/10/24.
 *
 * @see  客户端第二次启动，接收的消息也是不全的
 */
public class TimeServerNettyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws UnsupportedEncodingException {
        System.out.println("--服务端--读取---");
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        String body = new String(bytes,"utf8");

        //用 DelimiterBasedFrameDecoder 解析后的msg 是string 类型
        System.out.println("服务器收到消息 : "+msg);


        //响应消息
        String resp = "Hello--我给你回信了！-："+System.currentTimeMillis()+"****$_";
        ByteBuf respBuf = Unpooled.copiedBuffer(resp.getBytes());
        ctx.writeAndFlush(respBuf);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        System.out.println("--服务端--刷新---");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        System.out.println("--服务端--异常---");
        cause.printStackTrace();
        ctx.close();
    }
}

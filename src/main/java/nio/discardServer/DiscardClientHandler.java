package nio.discardServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.ByteBuffer;

/**
 * Created by ${xzl} on 2017/10/9.
 */
/**
 * Handles a server-side channel.
 */
public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
//                ⇽---  当被通知Channel是活跃的时候，发送一条消息
                CharsetUtil.UTF_8));
    }
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // discard
        ByteBuf in = (ByteBuf) msg;
        System.out.println(
//                ⇽---  记录已接收消息的转储
        "客户端接收 : " + in.toString(CharsetUtil.UTF_8));
//        try {
//            while(in.isReadable()){
//                System.out.println("--***客户端 测试 **-");
//                System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));//
////                System.out.println((char)in.readByte());
////                System.out.flush();
//            }
//        }finally {
//            ReferenceCountUtil.release(msg);//你可以在这里调用in.release()。
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}

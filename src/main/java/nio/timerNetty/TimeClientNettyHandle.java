package nio.timerNetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by ${xzl} on 2017/10/24.
 */
public class TimeClientNettyHandle extends SimpleChannelInboundHandler {
    private ByteBuf msg ;
//    public TimeClientNettyHandle(){
//        System.out.println("--客户端--构造器");
//        byte[] req = "客户端的消息".getBytes();
//        msg = Unpooled.buffer(req.length);
//        msg.writeBytes(req);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("--客户端--channelRead0 ："+msg);
        System.out.println("--客户端--读取--");
//        ByteBuf buf = (ByteBuf) msg; // (1)在TCP/IP中，NETTY会把读到的数据放到ByteBuf的数据结构中。
            // 解析方法1
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        String body = new String(bytes,"utf-8");
//        System.out.println("客户端接收的 ："+body);
//        System.out.println("client 读取..");
//        // 解析方法 2
//         buf = buf.readBytes(buf.readableBytes());
//        System.out.println("Client 接收:" + ByteBufUtil.hexDump(buf) + "; 结果是:" + buf.toString(Charset.forName("utf-8")));

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        //---检测到客户端活动---发送消息
        for(int i=0;i<10;i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer("  我是客户端----"+i+" :****$_", CharsetUtil.UTF_8)); // 必须有flush
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
//        System.out.println("--channelRead 读取--");
//        ByteBuf buf = (ByteBuf) msg; // (1)在TCP/IP中，NETTY会把读到的数据放到ByteBuf的数据结构中。
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        String body = new String(bytes,"utf-8");

        // 2. 用 DelimiterBasedFrameDecoder 解析后的msg 是string 类型
        System.out.println("客户端接到  ："+msg);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("--客户端--异常---");
        cause.printStackTrace();
        ctx.close();
    }


}

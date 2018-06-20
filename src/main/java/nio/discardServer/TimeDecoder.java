package nio.discardServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by ${xzl} on 2017/10/9.
 */
public class TimeDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readByte()<4){
            return;
        }
        out.add(in.readBytes(4));
        // public void initChannel(SocketChannel ch) throws Exception {
        // ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
    }
}

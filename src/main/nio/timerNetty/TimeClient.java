package main.nio.timerNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import main.nio.discardServer.TimeClientHandle;

import java.net.InetSocketAddress;

/**
 * Created by ${xzl} on 2017/10/24.
 */
public class TimeClient {
   public void connect(int port,String host) throws InterruptedException {
       //客户端线程组
       EventLoopGroup workGroup = new NioEventLoopGroup();
       try{
           Bootstrap  bootstrap = new Bootstrap();
           bootstrap.group(workGroup).channel(NioSocketChannel.class)
                   .remoteAddress(new InetSocketAddress(host, port)) // 绑定连接端口和host信息
                   .option(ChannelOption.TCP_NODELAY,true)
                   .handler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel ch) throws Exception {
                           //解决 粘包问题 1
//                           ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                           //解决 粘包问题 2  --- 分隔符
                           ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                           ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                           ch.pipeline().addLast(new StringDecoder());
                           ch.pipeline().addLast(new TimeClientNettyHandle());
                       }
                   });
           //发起异步连接
           ChannelFuture future = bootstrap.connect().sync();//host,port
           //等待客户端连接关闭
           future.channel().closeFuture().sync();
       }finally{
            workGroup.shutdownGracefully();
       }
   }

    public static void main(String[] args) throws InterruptedException {
        //&gt;
        int port;
        String address = "localhost";
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }else {
            port = 8088;
        }
        new TimeClient().connect(port,address);
    }
}

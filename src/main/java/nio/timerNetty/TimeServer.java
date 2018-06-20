package nio.timerNetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import nio.discardServer.DiscardServer;
import nio.discardServer.TimeServerHandler;
import nio.timerNetty.example.EchoServerHandler;

/**
 * Created by ${xzl} on 2017/10/24.
 */
public class TimeServer {
    public void bind(int port) throws InterruptedException {
        EventLoopGroup  bossGroup = new NioEventLoopGroup();
        EventLoopGroup  workGroup = new NioEventLoopGroup();
        try {
            //辅助启动类
            ServerBootstrap  bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    // 绑定监听端口
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChannelHandler());
//            .childHandler(new ChannelInitializer<SocketChannel>() { // 绑定客户端连接时候触发操作
//
//                @Override
//                protected void initChannel(SocketChannel ch) throws Exception {
//                    System.out.println("connected...; Client:" + ch.remoteAddress());
//                    ch.pipeline().addLast(new TimeServerNettyHandler());
//                    // 客户端触发操作
//                }
//            });
            //绑定端口 同步等待
            ChannelFuture  future = bootstrap.bind().sync();//port
            //等待服务监听端口关闭
            future.channel().closeFuture().sync();
        } finally {
            //优雅退出
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
    private  class ChildChannelHandler extends ChannelInitializer{
        @Override
        protected void initChannel(Channel ch) throws Exception {
            //解决 粘包问题 1
//            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
            //解决 粘包问题 2  --- 分隔符
            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
            ch.pipeline().addLast(new StringDecoder());
            ch.pipeline().addLast(new TimeServerNettyHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8888;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }else {
            port = 8088;
        }
        System.out.println("当前端口 : "+ port);
        new TimeServer().bind(port);
    }
}

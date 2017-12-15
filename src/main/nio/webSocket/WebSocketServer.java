package main.nio.webSocket;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by ${xzl} on 2017/11/2.
 */
public class WebSocketServer {
    public  void  run(int port){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("http-codec",new HttpServerCodec());
                    ch.pipeline().addLast("http-aggre",new HttpObjectAggregator(65546));
                    ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                    ch.pipeline().addLast("handler",new WebSocketServerHandler());
                }
            });
            Channel channel = bootstrap.bind(port).sync().channel();
            System.out.println("websocket 启动 port:"+port);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
//        if(args!=null && args.length > 0){
//            port = Integer.parseInt(args[0]);
//        }
        System.out.println("当前端口 : "+ port);
        new WebSocketServer().run(port);
    }
}

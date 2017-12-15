package main.nio.discardServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;


/**
 * Created by ${xzl} on 2017/10/9.
 */
public class DiscardServer {
    private  int port;
    public DiscardServer(int port){
        this.port = port;
    }

    public  void start() throws  Exception{
        //NioEventLoopGroup 是用来处理I/O操作的多线程事件循环器
        //用来接收进来的连接
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //用来处理已经被接收的连接，一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上
        // 如何知道多少个线程已经被使用，如何映射到已经创建的Channels上都需要依赖于EventLoopGroup的实现，并且可以通过构造函数来配置他们的关系。
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //ServerBootstrap 是一个启动NIO服务的辅助启动类。你可以在这个服务中直接使用Channel，
            ServerBootstrap bootstrap = new ServerBootstrap();
            //这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。
            // ChannelInitializer是一个特殊的处理类，他的目的是帮助使用者配置一个新的Channel
            bootstrap.group(workerGroup).channel(NioServerSocketChannel.class).
                    localAddress(new InetSocketAddress(port))//将本地地址设置为一个具有选定端口的InetSocket-Address
                    .childHandler(new
                    ChannelInitializer<SocketChannel>(){//这是关键。当一个新的连接被接受时，一个新的子Channel将会被创建，而ChannelInitializer将会把一个你的EchoServerHandler的实例添加到该
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
            //可以设置这里指定的通道实现的配置参数。我们正在写一个TCP/IP的服务端，因此我们被允许设置socket的参数选项比如tcpNoDelay和keepAlive
            .option(ChannelOption.SO_BACKLOG,128)
            //option()是提供给NioServerSocketChannel用来接收进来的连接。childOption()是提供给由父管道ServerChannel接收到的连接
            .childOption(ChannelOption.SO_KEEPALIVE,true);


//            ChannelFuture future = bootstrap.bind(port).sync();
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully().sync();
//            bossGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        int port;
        //&gt;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }else {
            port = 8088;
        }
        System.out.println("当前端口 : "+ port);
        new DiscardServer(port).start();
    }
}

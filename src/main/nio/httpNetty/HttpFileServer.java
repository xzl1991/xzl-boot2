package main.nio.httpNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by ${xzl} on 2017/10/31.
 */
public class HttpFileServer {
    private final String DEFAULT_PATH = "D:/test/temp";

    public void run(final int port,final String host){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap severStrap = new ServerBootstrap();
            severStrap.group(bossGroup,workGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                    ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                    ch.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                    ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                    ch.pipeline().addLast("fileServerHandler",new HttpFileServerHandler());
                }
            });
            ChannelFuture channelFuture = severStrap.bind(host,port).sync();
            System.out.println("服务启动----地址："+host+" ,端口:"+port);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8888;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }else {
            port = 8088;
        }
        new HttpFileServer().run(port,"127.0.0.1");
    }
}

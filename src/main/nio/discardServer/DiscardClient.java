package main.nio.discardServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by ${xzl} on 2017/10/9.
 */
public class DiscardClient {
    private  int port;
    private String address;
    public DiscardClient(String host, int port) {
        this.address = host;
        this.port = port;
    }
    public static void main(String[] args) throws Exception {
        //&gt;
        int port;
        String address = "127.0.0.1";
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }else {
            port = 8088;
        }
        new DiscardClient(address,port).start();
    }
    public  void start() throws Exception{
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup);
            bootstrap.channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(address,port));
//            bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new DiscardClientHandler());
                }
            });
        }finally {
            workGroup.shutdownGracefully().sync();
        }
    }
}

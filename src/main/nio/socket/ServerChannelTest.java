package main.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by ${xzl} on 2017/9/28.
 */
public class ServerChannelTest {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(888));

        //非阻塞模式
        serverSocketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(100);
        System.out.println("启动:");
        //1. ServerSocket serverSocket = new ServerSocket(999); 对比
        while (true){
           //2.  Socket socket = serverSocket.accept();//
            SocketChannel socketChannel = serverSocketChannel.accept();

            if(socketChannel!=null){//非阻塞模式
                System.out.println("新连接---");
                //
                StringBuilder sb = new StringBuilder();
                int byteRead = socketChannel.read(buffer);
                buffer.flip();
                System.out.println("呵呵 :"+Charset.forName("UTF-8").newDecoder().decode(buffer).toString());
//                String receivedString= Charset.forName("UTF-16").newDecoder().decode(buffer).toString();
                while (byteRead!=-1) {
                    while (buffer.hasRemaining()){
                        sb.append((char)buffer.get());
                        System.out.println("客户端消息1:"+sb.toString());
                    }
                }
                buffer.clear();
                System.out.println("客户端消息:"+sb);
            }
        }

    }
}

package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by ${xzl} on 2017/9/30.
 */
public class DatagramClient {
    public static void main(String[] args) throws IOException {
        //1. 打开连接
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        //2、绑定连接 --- 客户端要指定ip
//        datagramChannel.connect(new InetSocketAddress("localhost", 888));// 连接服务端
//        datagramChannel.connect(new InetSocketAddress(888));
//        datagramChannel.socket().bind(new InetSocketAddress(888));

        ByteBuffer byteBuffer = ByteBuffer.allocate(40);
        byteBuffer.clear();
        byteBuffer.put("国庆来了11221~".getBytes());
        byteBuffer.flip();
        datagramChannel.send(byteBuffer,new InetSocketAddress("localhost",888));
//        datagramChannel.write(byteBuffer);//,new InetSocketAddress(888));
    }
}

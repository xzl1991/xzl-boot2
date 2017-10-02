package main.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

/**
 * Created by ${xzl} on 2017/9/30.
 */
public class DatagramChannelTest {
    public static void main(String[] args) throws Exception {
        //1. 打开连接
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        //2、绑定连接
        datagramChannel.socket().bind(new InetSocketAddress(888));

        ByteBuffer byteBuffer = ByteBuffer.allocate(40);
        while (true) {
            byteBuffer.clear();
           if( datagramChannel.receive(byteBuffer)!=null){;//接收数据
               byteBuffer.flip();
               System.out.println("UDP收到数据 :" + Charset.forName("UTF-8").newDecoder().decode(byteBuffer).toString());
           }

        }
    }
}

package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by ${xzl} on 2017/9/29.
 *
 * <p>s</p>
 */
public class SocketChannelTest {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",4700));
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        /* 立即返回，已经连接返回true，否则false */
//        byteBuffer.put("国庆".getBytes());
        byteBuffer.put("国庆123456".getBytes("UTF-8"));
        byteBuffer.flip();
        ByteBuffer writeBuffer=ByteBuffer.wrap("国庆节来了！".getBytes("UTF-8"));
//        int bytesRead=socketChannel.read(writeBuffer);
        while (socketChannel.finishConnect()){
            while (byteBuffer.hasRemaining()&&socketChannel.read(byteBuffer)!=-1){
                System.out.println("服务器返回消息了-----");
//                socketChannel.write(writeBuffer);
                socketChannel.write(byteBuffer);
            }
            System.out.println("-----！服务器返回消息了-----");
        }
    }
}

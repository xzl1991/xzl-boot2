package nio.Selector;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ${xzl} on 2017/10/18.
 */
public class ServerSocketChannelTest implements Runnable{
//    public static void main(String[] args) throws IOException {
//        //1. 打开 ServerChannel
//        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        //2. 绑定 监听端口 ，设置非阻塞
//        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
//        serverSocketChannel.configureBlocking(false);
//        //3. 打开selector channel 必须是非阻塞状态
//        Selector selector = Selector.open();
//
//        //4. selector 注册
//        SelectionKey selectionKey = serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
//
//    }
    public int id = 100001;
    @Override
    public void run() {
        init();
    }

    public void init() {
        try {
            // 创建通道和选择器
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress( 4700);
//            InetSocketAddress inetSocketAddress = new InetSocketAddress(
//                    InetAddress.getLocalHost(), 4700);
            socketChannel.socket().bind(inetSocketAddress);
            // 设置通道非阻塞 绑定选择器
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT).attach(
                    id++);
            System.out.println("Server started .... port:4700");
            listener(selector);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public  void listener(Selector selector) throws IOException {
       while (true){
           int num = selector.select();// 阻塞 直到有就绪事件为止
           Set selectkeys = selector.selectedKeys();
           Iterator keyIterator = selectkeys.iterator();
           // 判断是哪个事件
           while (keyIterator.hasNext()){
               SelectionKey key = (SelectionKey) keyIterator.next();
               if(key.isAcceptable()){// 客户请求连接
                   // a connection was accepted by a ServerSocketChannel.
                   System.out.println(key.attachment()+" -- 接受请求连接");
                   // 获取通道 接受连接,
                   // 设置非阻塞模式（必须），同时需要注册 读写数据的事件，这样有消息触发时才能捕获
                   ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                   serverSocketChannel.accept().configureBlocking(false).register(selector,SelectionKey.OP_READ
                           | SelectionKey.OP_WRITE).attach(" 新连接:"+System.currentTimeMillis());
               }else if(key.isConnectable()){
                   // a connection was established with a remote server.
                   System.out.println(key.attachment()+ " - 连接事件");
               }else if(key.isReadable()){// 读数据
                   // a channel is ready for reading
                   System.out.println(key.attachment()+ " - 读数据事件");
                   SocketChannel clientChannel = (SocketChannel) key.channel();
                   ByteBuffer  byteBuffer = ByteBuffer.allocate(2048);
                   clientChannel.read(byteBuffer);
                   byteBuffer.flip();
                   System.out.println(key.attachment()+ " - 读取数据：" + getString(byteBuffer));
               }else if(key.isWritable()){
                   // a channel is ready for writing
                   System.out.println(key.attachment()+ " - 写 数据事件");
                   SocketChannel clientChannel = (SocketChannel) key.channel();
                   ByteBuffer writeBuf = ByteBuffer.allocate(2048);
                   String msg = "hello,我事实上是 world/n";
                   writeBuf.put(msg.getBytes());
                   writeBuf.flip();
                   clientChannel.write(writeBuf);
               }
               keyIterator.remove();
           }
       }
    }

    private String getString(ByteBuffer byteBuffer) throws CharacterCodingException, UnsupportedEncodingException {
//        StringBuilder sb = new StringBuilder(100);
//        int i= 0;
//        for(;i<byteBuffer.position();i++){
//            sb.append((char)byteBuffer.get(i));
//        }
//        return sb.toString();//new String(sb.toString().getBytes("utf-8"), "utf-8");
        return    Charset.forName("UTF-8").newDecoder().decode(byteBuffer).toString();
    }
}

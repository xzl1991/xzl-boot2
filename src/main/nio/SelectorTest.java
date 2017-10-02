package main.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ${xzl} on 2017/9/28.
 */
public class SelectorTest {
    public static void main(String[] args) throws Exception {
        //1. 打开 selector
        Selector selector = Selector.open();
        //2.channel  fileChannel不能设置非阻塞
        FileChannel fileChannel = new RandomAccessFile("D:/logs/test.txt","rw").getChannel();
        SocketChannel  socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //3.注册 selector
        SelectionKey  selectionKey = socketChannel.register(selector,SelectionKey.OP_READ);
        int readLength;//
        SelectionKey key;
        Set set;
        Iterator keyIterator ;
        while (true){
            readLength = selector.select();
            if(readLength==0) continue;
            set = selector.selectedKeys();//获取注册的channel
            keyIterator = set.iterator();
            while (keyIterator.hasNext()){
                key = (SelectionKey) keyIterator.next();
                if(key.isAcceptable()){
                    // a connection was accepted by a ServerSocketChannel.
                }else if(key.isConnectable()){
                    // a connection was established with a remote server.
                }else if(key.isReadable()){
                    // a channel is ready for reading
                }else if(key.isWritable()){
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }
}

package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by ${xzl} on 2017/9/27.
 */
public class ChannelBuff {
    public static void main(String[] args) throws Exception{
        FileChannel fileChannel = new RandomAccessFile("D:/logs/log.log","rw").getChannel();
        ByteBuffer byteBuffer  = ByteBuffer.allocate(5);
        int byteRead = fileChannel.read(byteBuffer);//读取
        System.out.println("文件的长度："+fileChannel.size());
//        fileChannel.truncate(4);
        while(byteRead != -1){
            System.out.println("读取的内容长度:"+byteRead);
            byteBuffer.flip();//首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据
//            fileChannel.position(3);//第一次读取的是abcde position 设置到3后 从d开始读取
            byteBuffer.position(3);//开始读取buff的位置
            while (byteBuffer.hasRemaining()){
                System.out.println(".内容.."+(char)byteBuffer.get()+", 当前位置"+fileChannel.position());
            }
            byteBuffer.clear();
//            fileChannel.position(23);
            byteRead = fileChannel.read(byteBuffer);
        }
        fileChannel.close();
    }
}

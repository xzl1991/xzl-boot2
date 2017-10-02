package main.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by ${xzl} on 2017/9/27.
 *
 * 该程序创建了一个128Mb的文件，如果一次性读到内存可能导致内存溢出，但这里访问好像只是一瞬间的事，这是因为，真正调入内存的只是其中的一小部分，其余部分则被放在交换文件上。这样你就可以很方便地修改超大型的文件了(最大可以到2 GB)。注意，Java是调用操作系统的"文件映射机制"来提升性能的
 *
 */
public class RandomAccessFileTest {
    static int length = 0x8000000; // 128 Mb
    public static void main(String[] args) {
        // 为了以可读可写的方式打开文件，这里使用RandomAccessFile来创建文件。
        try {
            FileChannel fileChannel = new RandomAccessFile("D:/logs/test.txt","rw").getChannel();
            //注意，文件通道的可读可写要建立在文件流本身可读写的基础之上

            //用内存映射文件替代 RandomAccessFile
            MappedByteBuffer  mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,length);
            //写128M的内容
            for (int i = 0; i < length; i++) {
                mappedByteBuffer.put((byte) 'x');
            }
            System.out.println("写入结束----");
            //读取文件中间6个字节内容
            for (int i = length / 2; i < length / 2 + 6; i++) {
                System.out.print((char) mappedByteBuffer.get(i));
            }
            fileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

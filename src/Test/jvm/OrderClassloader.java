package jvm;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @auther xzl on 14:26 2018/1/11
 */
public class OrderClassloader extends  ClassLoader{
    private String fileName;
    public OrderClassloader(String fileName){
        this.fileName = fileName;
    }

    protected Class<?> loadClass(String name,boolean resolve) throws ClassNotFoundException {
        // 1 .检查是否已加载
        Class re = findClass(name);
        if (re==null){
            System.out.println("无法加载类："+name +" ，需要从父加载器加载");
            return this.loadClass(name,resolve);
        }
        return null;
    }
    protected Class<?> findClass(String name){
        Class clazz = this.findLoadedClass(name);
        try {
            if(clazz==null){
                String classFile = getClassFile(fileName);
                FileInputStream  inputStream = new FileInputStream(classFile);
                FileChannel fileChannel = inputStream.getChannel();
                ByteArrayOutputStream bop = new ByteArrayOutputStream();
                WritableByteChannel  wbc = Channels.newChannel(bop);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (true){
                    int i = fileChannel.read(buffer);
                    if(i==0||i==-1){
                        break;
                    }
                    buffer.flip();
                    wbc.write(buffer);
                    buffer.clear();
                }
                inputStream.close();
                byte[] bytes = bop.toByteArray();
                clazz = defineClass(name,bytes,0,bytes.length);
            }
        }catch (Exception e){

        }

        return clazz;
    }

    private String getClassFile(String fileName) {

        return fileName.replace('/', '.');
    }
}

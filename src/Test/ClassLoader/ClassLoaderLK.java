package Test.ClassLoader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by ${xzl} on 2017/6/29.
 */
public class ClassLoaderLK extends  ClassLoader{
    private ClassLoader parent = null; // parent classloader
    private String path;
    public ClassLoaderLK(ClassLoader currentClassloader, String pp) {
        super(currentClassloader);
        this.parent = currentClassloader;// 这样做其实是无用的
        this.path = pp;
    }

    public static void main(String[] args) {
        String bootPath = "sun.boot.class.path";
        System.out.println("BootPathLoader : "+System.getProperty(bootPath));
        String ext = "java.ext.dirs";
        System.out.println("java.ext.dirs : "+System.getProperty(ext));
        String cp = "java.class.path";
        System.out.println("AppClassLoader : " + System.getProperty(cp));

        ClassLoader currentClassloader = ClassLoaderLK.class.getClassLoader();
        System.out.println("当前loader : "+currentClassloader);

//        String pp = "d:\\testcl\\";
        //E:\git\testSession\src\Test\ClassLoader
        String pp = "E:\\git\\testSession\\Test\\ClassLoader";
        ClassLoaderLK classLoaderLK = new ClassLoaderLK(currentClassloader,pp);
        String name = "HelloBean.class";
//        name = "HelloBean";
        try {
            Class<?> loadClass = classLoaderLK.loadClass(name);
            Object object = loadClass.newInstance();
            System.out.println();
            System.out.println(" invoke 方法:");
            Method method = loadClass.getMethod("hello");
            method.invoke(object);
            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * <P>重写方法</P>
     * */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        return loadClass(name, false);
        Class<?> cls = findLoadedClass(name);
        if(cls==null){
//            cls = getSystemClassLoader().loadClass(name); // SystemClassLoader 会从classpath下加载
//            if (cls == null) {(2)
            // 默认情况下， 当前cl的parent是 SystemClassLoader，
            // 而当前cl的parent的parent 才是ExtClassLoader
            ClassLoader parent2 = getParent().getParent();
            System.out.println("parent2  : " + parent2);
            System.out.println("尝试使用 ExtClassLoader 加载class : " + name);
            cls = parent2.loadClass(name);
            if(cls == null){
                System.out.println("尝试使用 ClassLoaderLK 加载class : " + name);
                cls = findClass(name);
                if(cls==null){
                    System.out.println("ClassLoaderLK 加载 :"+name+" 失败");
                }else {
                    System.out.println("ClassLoaderLK 加载:"+name+" 成功");
                }
            } else {
                System.out.println("ExtClassLoader 加载class:" + name + " Successful");
            }
        }
        return  cls;
    }
    /**
     * <P>重写 findClass</P>
     * */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println( "尝试 findClass :" + name);
        InputStream is = null;
        Class class1 = null;
        String classPath = name.replace(".", "\\") + ".class";
        String classFile = path + classPath;
        byte[] data = getClassFileBytes(classFile);
        class1 = defineClass(name,data,0,data.length);
        if (class1 == null) {
            System.out.println("ClassLoaderLK.findClass() ERR ");
            throw new ClassFormatError();
        }
        return class1;
//        throw new ClassNotFoundException(name);
    }

    private byte[] getClassFileBytes(String classFile) {
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
             fileInputStream = new FileInputStream(classFile);
            FileChannel fileChannel = fileInputStream.getChannel();
            byteArrayOutputStream = new ByteArrayOutputStream();
            WritableByteChannel writableByteChannel = Channels.newChannel(byteArrayOutputStream);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i= fileChannel.read(byteBuffer);
                if(i<=0){
                    break;
                }
                byteBuffer.flip();
                writableByteChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  byteArrayOutputStream.toByteArray();
    }
}

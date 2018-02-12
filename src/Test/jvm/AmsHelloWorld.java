package jvm;

import aj.org.objectweb.asm.ClassWriter;
import aj.org.objectweb.asm.MethodVisitor;
import aj.org.objectweb.asm.Opcodes;

import java.lang.reflect.InvocationTargetException;

/**
 * @auther xzl on 16:12 2018/1/10
 * 测试 asm
 */
public class AmsHelloWorld extends ClassLoader implements Opcodes{
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        // COMPUTE_MAXS 自动计算最大局部变量表 和 最深操作数栈
        //COMPUTE_FRAMES 自动计算映射帧栈
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
        //设置类的 基本信息
        cw.visit(V1_7,ACC_PUBLIC,"Example",null,"java/lang/Object",null);
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC,"<init>","()V",null,null);
        mv.visitVarInsn(ALOAD,0);
        mv.visitMethodInsn(INVOKESPECIAL,"java/lang/Object","<init>","()V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(0,0);
        mv.visitEnd();

        mv = cw.visitMethod(ACC_PUBLIC+ACC_STATIC,"main","([Ljava/lang/String;)V",null,null);
        mv.visitFieldInsn(GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello World,AMS");
        mv.visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(0,0);
        mv.visitEnd();

        byte[] codes = cw.toByteArray();
        AmsHelloWorld amsHelloWorld = new AmsHelloWorld();
        Class exampleClass = amsHelloWorld.defineClass("Example",codes,0,codes.length);
        exampleClass.getMethods()[0].invoke(null,new Object[]{ null });
    }
}

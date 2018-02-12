package jvm;


/**
 * @auther xzl on 19:15 2018/1/10
 */
public class BootLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader1 = BootLoaderTest.class.getClassLoader().getParent();
        System.out.println(loader1);
        System.out.println("---------------");

        System.out.println(loader1.loadClass("utils.Loader").newInstance().getClass().getClassLoader());
    }
}

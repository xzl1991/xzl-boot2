package jvm;

/**
 * @auther xzl on 14:11 2018/1/8
 */
public class AllocEden {
    public static final  int K = 1024;

    public static void main(String[] args) {
        for(int i=0;i<50;i++){
            byte[] b = new byte[K];
        }
    }
}

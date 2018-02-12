package jvm;

/**
 * @auther xzl on 10:46 2018/1/5
 */
public class TestStackDeep {
    private  static int count = 0;
    public static void main(String[] args) {
        try {
            function();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 提示 stack 最小值为 104k .....
            System.out.println("当前栈深度: "+count);
        }
    }
    public  static void function(){
        count++;
        function();
    }
}

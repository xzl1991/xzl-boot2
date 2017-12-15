package qlcoder;


/**
 */
public class Test1 {
    public static void main(String[] args) {
        int num = 0;
        int current = 1;
        int[][] arr;
        String[][] arr1;
        Object[][] ar1;
        Object[] ar2[];
        byte b = (byte)129;
        System.out.println("************" + b);
        for(;;){
            current++;
            if(current%2==0||current%3==0){
                num++;
            }
            if(num==2333){
                System.out.println("最终值是:"+current);
                break;
            }

        }
    }
}

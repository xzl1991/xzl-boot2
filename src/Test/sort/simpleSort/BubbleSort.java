package sort.simpleSort;

import org.junit.Test;
import sort.BaseSort;

import java.util.Arrays;

import static sun.misc.Version.print;

/**
 * @auther xzl on 14:38 2018/2/1
 * 1. 冒泡排序最简单的
 */
public class BubbleSort extends BaseSort {
//    int[]  val = new int[]{1,2,5,8,4,7,6};
    public static void main(String[] args) {
        System.out.println(val.length);
        BubbleSort test = new BubbleSort();
        test.bubbleSort(val);
        System.out.println("结果:");
        test.printLn(val);
        test.bubbleSortStand(val);
        System.out.println("标准结果:");
        test.printLn(val);
    }

    /**
     * 将序列中所有元素两两比较，将最大的放在最后面。
     将剩余序列中所有元素两两比较，将最大的放在最后面。
     重复第二步，直到只剩下一个数。
     * */
    @Test
    public void bubbleSort(int[] param){
        int a = 0;
        for (int i=0;i<param.length-1;i++){
            for (int j=i+1;j<param.length;j++){
                a +=1;
                if (param[i]>param[j]){
                    swap(param,i,j);
                }
            }
        }
        System.out.println("循环次数: "+a);
    }
    /**
     * 网上标准写法。。。。。
     * */
    public void bubbleSortStand(int[] param){
        int a = 0;
        for (int i=0;i<param.length-1;i++){
            for(int j = 0;j < param.length - 1-i;j++){
                a +=1;
                if(param[j] > param[j+1])
                {
                    int temp = param[j];
                    param[j] = param[j+1];
                    param[j+1] = temp;
                }
            }
        }
        System.out.println("标准循环次数: "+a);
    }

}

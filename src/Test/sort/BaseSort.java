package sort;

import java.util.Arrays;

/**
 * @auther xzl on 15:10 2018/2/1
 *
 * 稳定的
 *    插入排序、冒泡排序、                    O(n2)
 *    二叉树排序、二路归并排序及其他线形排序  O(n);
 *    线形排序、二路归并排序的辅助空间为O(n),其它排序的辅助空间为O(1);
 *    其它非线形排序的时间复杂性为O(nlog2n)
 * 不稳定
 *    选择排序、                              O(n2)
 *    希尔排序、快速排序、堆排序
 */
/**
 * 速度比较：
 * 插入、冒泡排序的速度较慢，但参加排序的序列局部或整体有序时，这种排序能达到较快的速度
 *
 * 1.当n较小时，对稳定性不作要求时宜用选择排序，
 *         对稳定性有要求时宜用插入或冒泡排序。
 *       若待排序的记录的关键字在一个明显有限范围内时,且空间允许是用桶排序。
 *
   2.当n较大时，关键字元素比较随机，对稳定性没要求宜用快速排序。

   3.当n较大时，关键字元素可能出现本身是有序的，对稳定性有要求时，空间允许的情况下。
        宜用归并排序。

 * 4.当n较大时，关键字元素可能出现本身是有序的，对稳定性没有要求时宜用堆排序。
 * */
public class BaseSort {
    public static int[]  val = {9,13,0,1,2,5,8,4,7,6};
    public static void printLn(int[] param) {
        Arrays.stream(param).forEach(temp-> System.out.print(temp+" ,"));
    }

    public static void swap(int[] param,int i,int j) {
        int temp = param[i];
        param[i] =  param[j];
        param[j] =  temp;
    }
}

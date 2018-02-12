package sort.simpleSort;

import org.junit.Test;
import sort.BaseSort;

/**
 * @auther xzl on 15:11 2018/2/1
 * 直接插入排序： n2  稳定
 * 每一步将一个待排序的记录，插入到前面已经排好序的有序序列中去，直到插完所有元素为止。
 */
public class DirectInsertSort extends BaseSort{
    public static void main(String[] args) {
        int[] val = {32,43,23,13,11};
        DirectInsertSort direct = new  DirectInsertSort();
        direct.insertSort(val);
        printLn(val);
    }
    public void insertSort(int[] arr){
        int a = 0;
        for (int i=1;i<arr.length;i++){
            int j = i;
            while (j>0 && arr[j]<arr[j-1]){
                a +=1;
                swap(arr,j,j-1);
                j--;
            }
        }
        System.out.println("循环次数: "+a);
    }
}

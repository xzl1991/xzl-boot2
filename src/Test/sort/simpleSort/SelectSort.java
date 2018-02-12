package sort.simpleSort;

import sort.BaseSort;

/**
 * @auther xzl on 16:15 2018/2/1
 *  O(n2)
 * 每一趟从待排序的数据元素中选择最小（或最大）的一个元素作为首元素，直到所有元素排完为止，
 * 简单选择排序是不稳定排序。
 */
public class SelectSort extends BaseSort{
    public static void main(String[] args) {
        SelectSort selectSort = new  SelectSort();
        selectSort.selectSort(val);
        printLn(val);
    }

    public void selectSort(int[] arr){
        int a = 0;
        for (int i=0;i<arr.length-1;i++){
            int min = i;//每一趟循环比较时，min用于存放较小元素的数组下标，
            // 这样当前批次比较完毕最终存放的就是此趟内最小的元素的下标，避免每次遇到较小元素都要进行交换。
             for (int j=i+1;j<arr.length;j++){
                 a +=1;
                if (arr[j]<arr[min]){
                    min = j;
                }
            }
            //进行交换，如果min发生变化，则进行交换
            if (min != i) {
                swap(arr,min,i);
            }
        }
        System.out.println("循环次数: "+a);
    }
}

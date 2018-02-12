package sort.otherSort;

import sort.BaseSort;

/**
 * @auther xzl on 17:14 2018/2/5
 *O(nlogn)
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 */
public class QuickSort extends BaseSort {
    public static void main(String[] args) {
        int[] val = {46 ,30 ,82 ,90 ,56 ,17 ,95,15};
        new QuickSort().quickSort(val,0,val.length-1);
        printLn(val);
    }
    public void quickSort(int[] arr,int low,int hight){
        if (low<hight){
            int mid = getMid(arr,low,hight);//数组分开
            quickSort(arr,low,mid-1);//对低字表进行递归排序
            quickSort(arr,mid+1,hight);//对高字表进行递归排序
        }

    }
    public int getMid(int[] arr,int low,int hight){
        int tmp = arr[low];//数组的第一个作为中轴
        while (low<hight){
            while (low<hight && arr[hight] >= tmp){
                hight--;
            }
            arr[low] = arr[hight];//比中轴小的记录移到低端
            while (low<hight && arr[low] <= tmp){
                low++;
            }
            arr[hight] = arr[low];//比中轴大的记录移到高端
        }
        arr[hight] = tmp;//中轴记录到尾
        System.out.println(java.util.Arrays.toString(arr));
        return low;
    }


    //方法2 ==============
    /**
     * @param arr
     * @param left  左指针
     * @param right 右指针
     */
    public static void quickSort1(int[] arr, int left, int right) {
        if (left < right) {
            //获取枢纽值，并将其放在当前待处理序列末尾
            dealPivot(arr, left, right);
            //枢纽值被放在序列末尾
            int pivot = right - 1;
            //左指针
            int i = left;
            //右指针
            int j = right - 1;
            while (true) {
                while (arr[++i] < arr[pivot]) {
            }
                while (j > left && arr[--j] > arr[pivot]) {
                }
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            if (i < right) {
                swap(arr, i, right - 1);
            }
            quickSort1(arr, left, i - 1);
            quickSort1(arr, i + 1, right);
        }

    }

    /**
     * 处理枢纽值
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap1(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap1(arr, left, right);
        }
        if (arr[right] < arr[mid]) {
            swap1(arr, right, mid);
        }
        swap1(arr, right - 1, mid);
    }

    /**
     * 交换元素通用处理
     *
     * @param arr
     * @param a
     * @param b
     */
    private static void swap1(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
































































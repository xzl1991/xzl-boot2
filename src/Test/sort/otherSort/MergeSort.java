package sort.otherSort;

import sort.BaseSort;

/**
 * @auther xzl on 11:27 2018/2/5
 *平均时间复杂度为O(nlogn)。而且，归并排序的最好，最坏，平均时间复杂度均为O(nlogn)。
 * 其空间复杂度O(n)
 * 稳定排序
 */
public class MergeSort extends BaseSort {
    public static void main(String[] args) {
        int []arr = {49,38,65,97,76,13,27};
        printLn(arr);
        System.out.println("原始数据:");
        new MergeSort().mergeSort(arr);
        printLn(arr);
    }
    //1.自定义归并
    public void mergeSort(int[] arr){
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
    }
    private void mergeSort(int[] arr,int left,int right,int []temp){
       if (right>left){
           int mid = (left+right)/2;
           mergeSort(arr,left,mid,temp);//左边递归
           mergeSort(arr,mid+1,right,temp);//右边递归
           merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
           System.out.println(java.util.Arrays.toString(arr));
       }
    }

    private void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left ;// 左边序列指针
        int j = mid+1;// 左边序列指针
        int t = 0 ;// 数组指针
        while (i<=mid&&j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }

    }

}














































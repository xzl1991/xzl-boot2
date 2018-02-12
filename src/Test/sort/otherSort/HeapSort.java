package sort.otherSort;

import org.junit.Test;
import sort.BaseSort;

import java.util.Arrays;

/**
 * @auther xzl on 13:31 2018/2/2
 * 堆排序：O(nlogn)
 * 堆排序是一种选择排序，最坏，最好，平均时间复杂度均为O(nlogn)，
 * 它也是不稳定排序
 * 堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 */
public class HeapSort extends BaseSort {
    /**
     * 将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。
     * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
     */
    volatile int a = 0;
    volatile int[] aa = {1, 2, 3};

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
//        heapSort.heapSort(val);
//        int[] val = {32,43,23,13,11};

        int[] arr = {11, 7, 18, 3, 5, 4, 10, 9};
//        heapSort.heapSort(arr);
//        printLn(arr);
        heapSort1(arr);
    }

    /**
     * 1.自定义方法
     */
    public void heapSort(int[] arr) {
        //1.构建堆
        //2.把声音n-1个元素构建
        //a.假设给定无序序列结构
        for (int i = arr.length; i > 1; i--) {
            //2.此时我们从最后一个非叶子结点开始（arr.length/2-1）
            // ，从左至右，从下至上进行调整。
            //最后一个飞叶子节点
            buildHeap(arr, i);
            //结束交换头尾元素
            int temp = arr[i - 1];
            arr[i - 1] = arr[0];
            arr[0] = temp;
        }
    }

    public void buildHeap(int[] arr, int i) {//可以把最后一个非叶节点 和 新堆的截止位一起传过来
        int a = i / 2 - 1;
        for (; a >= 0; a--) {//建堆
            int last = a;
            //左右节点
            int left = last * 2 + 1;
            int right = last * 2 + 2;
            if (right >= i) {//因为数字下标从0开始~~~
                continue;
            }
            if (arr[last] >= arr[left] && arr[last] >= arr[right]) {
                continue;
            }
            if (arr[last] < arr[left]) { //可以 改成一次取3个节点里的方法
                swap(arr, last, left);
            }
            if (right < arr.length && arr[last] < arr[right]) {
                swap(arr, last, right);
            }
        }
    }


    //方法2：
    public static void heapSort1(int[] data){
        System.out.println("开始排序");
        for(int i=0;i<data.length;i++){
//依次创建堆
            buildMaxHeap(data, data.length-1-i);
            System.out.println("第"+i+"次创建堆:"+java.util.Arrays.toString(data));
//交换建堆后 第一个和最后一个元素
            swap(data,0, data.length-1-i);
            System.out.println(java.util.Arrays.toString(data));
        }
    }
    //对数组从0 到  lastIndex 创建大顶堆
    private static void buildMaxHeap(int[] data, int lastIndex) {
//每次比较 最后一个节点的 父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
//保存当前正在判断的节点
            int k = i;
//判断当前节点子点是否存在
            while (k * 2 + 1 <= lastIndex) {

// 如果 k*2+1 < lastIndex. 代表又节点存在
                int biggerIndex = k * 2 + 1;
                if (biggerIndex < lastIndex) {
//比较左右节点大小
//右节点大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        biggerIndex++;
                    }
                }
//比较父节点 和 较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    swap(data, k, biggerIndex);


//将 被替换的 位置的 新元素 与其下 的 子元素比较
                    k = biggerIndex;
                } else {
                    break;
                }
// System.out.println("第"+i+"次移动:"+java.util.Arrays.toString(data));
            }

        }
//判断大小 交换数据
    }
}



















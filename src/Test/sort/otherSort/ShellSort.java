package sort.otherSort;

import sort.BaseSort;

import java.util.Arrays;

/**
 * @auther xzl on 16:50 2018/2/1
 *
 * 插入排序 : 插入排序的改进
 * 　希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，
 *   每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 */
public class ShellSort extends BaseSort{
    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
//        shellSort.shellSort(val);
        shellSortMax(val);
//        int[] val = {32,43,23,13,11};
//        shellSort.shellSort(val);
//        sort(val);
//        shellSortMax(val);
        printLn(val);
    }

    // 1.
    public void shellSort(int[] arr){
        int increment = arr.length/2;// 希尔增量
        int a = 0;
       while (increment>= 1){
           for (int i=increment;i<arr.length;i++){
               int j = i;
               while (j-increment>=0 && arr[j]<arr[j-increment]){
                   a +=1;
                   swap(arr,j,j-increment);
                   j-=increment;
               }
           }
//           for (int i=increment;i<arr.length;i++){
//               //进行插入排序
//               int temp = arr[i];
//               int j=i;
//               while (j-increment>=0&&arr[j]<arr[j-increment]){//for循环先减有问题
//                   a +=1;
//                   swap(arr,j-increment,j);//前后位置交换
//                   j-=increment;
//               }
////               for (;j>=0&&arr[j]<arr[j+increment];){//没想到
////                   a +=1;
////                   swap(arr,j+increment,j);//前后位置交换
////                   j-=increment;
////               }
//           }
           increment = increment/2;
       }
        System.out.println("循环次数: "+a);
    }
    // 2.
    public static void shellSortMax(int[] data) {
        // 计算出最大的h值
        int h = 1;
        int a = 0;
        while (h <= data.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int i = h; i < data.length; i += h) {
                if (data[i] < data[i - h]) {
                    int tmp = data[i];
                    int j = i - h;
                    while (j >= 0 && data[j] > tmp) {
                        data[j + h] = data[j];
                        j -= h;
                        a++;
                    }
                    data[j + h] = tmp;
//                    printLn(data);
                }
            }
            // 计算出下一个h值
            h = (h - 1) / 3;

        }
        System.out.println("循环次数: "+a);
    }
    /**
     * 3.
     * */
    public void shellSortNew(int[] arr){
        //1.增量 inrce
//       while ()
    }
    /**
     *  3.
     * 希尔排序 针对有序序列在插入时采用交换法
     * @param arr
     */
    public static void sort(int []arr){
        //增量gap，并逐步缩小增量
        for(int increment=arr.length/2;increment>0;increment/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=increment;i<arr.length;i++){
                int j = i;
                while(j-increment>=0 && arr[j]>arr[j-increment]){
                    //插入排序采用交换法
                    swap(arr,j,j-increment);
                    j-=increment;
                }
            }
        }
    }

    /**
     * 4.
     * 希尔排序 针对有序序列在插入时采用移动法。
     * @param arr
     */
    public static void sort1(int []arr){
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
                int j = i;
                int temp = arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0 && temp<arr[j-gap]){
                        //移动法
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}







































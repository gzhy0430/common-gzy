package com.indi.argorithm;

import java.util.Arrays;

/**
 * 排序算法分为
 * 比较类排序(交换排序<冒泡排序 快速排序>、插入排序<简单插入排序 希尔排序>、选择排序<简单选择排序 堆排序>、归并排序<二路归并排序 多路归并排序>)
 * 非比较类排序(计数排序、桶排序、基数排序)
 * 排序方式 时间复杂度(平均)   时间复杂度(最坏)   时间复杂度(最好)   空间复杂度   稳定性
 * 冒泡排序     O(n²)               O(n²)           O(n)                O(1)        Y
 * 快速排序     O(nlogn)            O(n²)           O(nlogn)            O(nlogn)    N
 *
 * 插入排序     O(n²)               O(n²)           O(n)                O(1)        Y
 * 希尔排序     O(n的1.3次方)       O(n²)           O(n)                O(1)        N
 *
 * 选择排序     O(n²)               O(n²)           O(n²)               O(1)        N
 * 堆排序       O(nlogn)            O(nlogn)        O(nlogn)            O(1)        N
 *
 * 归并排序     O(nlogn)            O(nlogn)        O(nlogn)            O(n)        Y
 *
 * 计数排序     O(n+k)              O(n+k)          O(n+k)              O(n+k)      Y
 * 桶排序       O(n+k)              O(n²)           O(n)                O(n+k)      Y
 * 基数排序     O(n*k)              O(n*k)          O(n*k)              O(n+k)      Y
 *
 * 稳定和不稳定概念：原本a在b前面，若a==b,排序之后a仍然在b前面则稳定，若不确定则不稳定
 * 时间复杂度：对排序数据总的操作次数，反映n变化时，操作次数呈现的规律
 * 空间复杂度：算法在计算机内执行时所需存储空间的度量
 */
public class SortX {
    public static void main(String[] args) {
        int[] arr = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        Arrays.sort(arr);
        shellsort(arr);
//        insertionsort(arr);
//        bubblesort(arr);
//        selectsort(arr);
    }

    /*
     * 基数排序--
     */
    static void radixsort(int arr, int maxDigit){

    }

    /*
     * 桶排序--
     */
    static void bucketsort(int[] arr, int bucketsize){

    }

    /*
     * 计数排序--
     */
    static void countsort(int[] arr, int maxValue){

    }

    /*
     * 堆排序--
     */
    static void heapsort(int[] arr){
//        ？？？？
    }

    /*
     * 快速排序--通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分关键字小，
     * 则可分别对这两部分继续记录进行排序，使整个序列有序
     */
    static void quicksort(int[] arr){

    }

    /*
     * 归并排序--先使子序列有序，合并已有序的子序列
     */
    static void mergesort(int[] arr){
//        ??????
    }

    /*
    * 希尔排序--缩小增量排序：是简单插入排序的改进版--优先比较距离较远的元素
    */
    static void shellsort(int[] arr){
        for(int m = (int) Math.floor(arr.length / 2); m > 0; m = (int) Math.floor(m/2)){
            for(int n = m; n < arr.length; n++){
                int j = n;
                int current = arr[n];
                while(j - m >= 0 && current < arr[j - m]){
                    arr[j] = arr[j - m];
                    j = j - m;
                }
                arr[j] = current;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序：从第一个元素开始，该元素默认已经被排序
     * 去下一个元素，在已经排序的序列中从后向前扫描
     * 插入到比他小的元素的后一个位置
     */
    static void insertionsort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int tmp = arr[i];
            int preIndex = i - 1;
            while(preIndex >= 0 && arr[preIndex] > tmp){
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = tmp;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序：找到最大(小)值，和序列头(尾)交换，直到所有元素都排序完毕
     */
    static void selectsort(int[] arr){
        int tmp;
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[minIndex]){//寻找最小的数的下标
                    minIndex = j;
                }
            }
            tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
            /*arr[i] ^= arr[minIndex];
            arr[minIndex] = arr[i]^arr[minIndex];
            arr[i] ^= arr[minIndex];*/
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序：
     * 比较相邻的两个元素，若大则交换
     * 对每一对相邻的元素做同样的工作，从最开始一对到最后一对，如此，最后的元素会是最大值
     * 针对所有的元素重复以上步骤除了最后一个
     * 重复1~3,直到排序完成
     *  */
    static void bubblesort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                if(arr[j] > arr[j + 1]){
                    /*arr[j] ^= arr[j+1];
                    arr[j+1] = arr[j]^arr[j+1];
                    arr[j] ^= arr[j+1];*/
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j] ^ arr[j+1];
                    arr[j] = arr[j] ^ arr[j+1];
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    static void sort2(int[] arr){

    }
}

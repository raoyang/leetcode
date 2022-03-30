package org.sunshine.lc.test.sort.source;

public class 快速排序 {


    private static void quickSort(int arr[], int low, int high){
        if(low >= high){
            return;
        }
        int k = arr[low];
        int i = low;
        int j = high;
        while(i < j){
            while(i < j && arr[j] >= k)
                j--;
            swap(arr, i, j);
            while(i < j && arr[i] <= k)
                i++;
            swap(arr, i, j);
        }
        quickSort(arr, 0, i - 1);
        quickSort(arr, i + 1, j);
    }

    /***
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

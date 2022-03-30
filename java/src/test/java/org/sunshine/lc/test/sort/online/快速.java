package org.sunshine.lc.test.sort.online;

public class 快速 {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        sort(arr, 0, arr.length - 1);
        System.out.print(arr);
    }

    private static void sort(int arr[], int start, int end){
        if(start >= end){
            return;
        }
        int k = arr[start];
        int i = start; //头指针
        int j = end; //尾指针
        while(i < j){
            while(i < j && arr[j] >= k)
                j --;
            swap(arr, j, i);
            while(i < j && arr[i] < k)
                i ++;
            swap(arr, i, j);
        }
        sort(arr, start, i);
        sort(arr, i+1, end);
    }

    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

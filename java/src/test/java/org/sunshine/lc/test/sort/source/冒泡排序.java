package org.sunshine.lc.test.sort.source;

public class 冒泡排序 {

    public static void main(String args[]){
        int arr[] = {4,3,2,5,8,1,6,7};
        bubbleSort(arr);
        System.out.print(arr);
    }

    private static void bubbleSort(int arr[]){
        for(int i = 0; i < arr.length ; i ++){
            for(int j = arr.length - 1 ; j > i; j --){
                if(arr[j] < arr[j-1]){
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

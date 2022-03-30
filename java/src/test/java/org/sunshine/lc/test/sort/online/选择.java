package org.sunshine.lc.test.sort.online;

public class 选择 {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        sort(arr);
        System.out.print(arr);
    }

    private static void sort(int arr[]){
        for(int i = 0 ; i < arr.length ; i ++){
            for(int j = arr.length - 1 ; j > i ; j --){
                if(arr[j] < arr[i]){
                    swap(arr, j , i);
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

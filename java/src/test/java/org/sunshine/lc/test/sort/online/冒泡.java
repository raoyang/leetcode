package org.sunshine.lc.test.sort.online;

public class 冒泡 {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        sort(arr);
        System.out.print(arr);
    }

    private static void sort(int arr[]){
        if(arr == null || arr.length < 2){
            return;
        }
        for(int i = 0 ; i < arr.length; i ++){
            for(int j = arr.length - 1; j > i ; j --){
                if(arr[j] < arr[j - 1]){
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

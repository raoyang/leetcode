package org.sunshine.lc.test.sort.online;

public class 直插 {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        sort(arr);
        System.out.print(arr);
    }

    private static void sort(int arr[]){
        for(int i = 1 ; i < arr.length ; i ++){
            int temp = arr[i];
            int j;
            for(j = i-1 ; j >= 0; j --){
                if(temp < arr[j]){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = temp;
        }
    }

    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

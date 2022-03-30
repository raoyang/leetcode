package org.sunshine.lc.test.sort.online;

public class 希尔 {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        sort(arr);
        System.out.print(arr);
    }

    private static void sort(int arr[]){
        int len = arr.length;
        for(int gap = len / 2 ; gap > 0 ; gap /= 2){
            //每次gap之后，把同一个gap上的数组，按照直接插入排序的方式重排
            for(int i = gap; i < arr.length; i ++){
                int temp = arr[i];
                int j;
                for(j = i-gap ; j >= 0 ; j = j-gap){
                    if(temp < arr[j]){
                        arr[j+gap] = arr[j];
                    }else{
                        break;
                    }
                }
                arr[j+gap] = temp;
            }
        }
    }

    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

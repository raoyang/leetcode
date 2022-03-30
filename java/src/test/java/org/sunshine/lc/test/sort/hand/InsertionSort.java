package org.sunshine.lc.test.sort.hand;

public class InsertionSort {

    public static void main(String args[]) {
        //int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        int arr[] = {6,5,4,3,2,1};
        sort(arr);
        System.out.print(arr);
    }

    private static void sort(int []arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        //从第二个元素开始，第一个是有序的
        for(int i = 1 ; i < arr.length ; i ++) {
            int temp = arr[i];
            int j;
            for(j = i; j > 0 && temp < arr[j-1]; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
    }
}

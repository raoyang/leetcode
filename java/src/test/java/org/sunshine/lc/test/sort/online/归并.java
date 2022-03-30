package org.sunshine.lc.test.sort.online;

import java.util.Arrays;

public class 归并 {
    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        int s[] = sort(arr);
        System.out.print(s);
    }

    private static int[] sort(int arr[]){
        if(arr.length < 2){
            return arr;
        }
        int middle = arr.length / 2;
        int a[] = Arrays.copyOfRange(arr, 0, middle);
        int b[] = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(sort(a), sort(b));
    }

    /***
     * 合并两个有序数组
     * @param a 有序数组a
     * @param b 有序数组b
     */
    private static int[] merge(int a[], int b[]){
        int []arr = new int[a.length + b.length];

        int i=0, j=0, k=0;
        while(i < a.length && j < b.length){
            while(i < a.length && j < b.length && a[i] <= b[j])
                arr[k++] = a[i++];
            while(i < a.length && j < b.length && b[j] < a[i])
                arr[k++] = b[j++];
        }
        while(i < a.length){
            arr[k++] = a[i++];
        }
        while(j < b.length){
            arr[k++] = b[j++];
        }
        return arr;
    }

    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

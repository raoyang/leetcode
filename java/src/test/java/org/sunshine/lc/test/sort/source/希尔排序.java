package org.sunshine.lc.test.sort.source;

public class 希尔排序 {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        sort(arr);
        System.out.print(arr);
    }

    public static void sort(int[] arr) {
        for (int gap = arr.length / 2; gap >  0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j;
                for (j = i; j >= gap && tmp < arr[j - gap] ; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }
}

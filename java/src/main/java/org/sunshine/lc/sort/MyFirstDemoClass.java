package org.sunshine.lc.sort;

public class MyFirstDemoClass {

    public static void main(String args[]) {
        int arr[] = {6,5,4,3,2,1};
        Insert(arr);
        System.out.println(arr);
    }

    private static void Insert(int arr[]) {
        if(arr == null || arr.length < 2) {
            return;
        }
        for(int i = 1; i < arr.length ; i ++) {
            int val = arr[i];
            int j;
            for(j = i; j > 0 && arr[j-1]>val; j --) {
                arr[j] = arr[j - 1];
            }
            arr[j] = val;
        }
    }
}

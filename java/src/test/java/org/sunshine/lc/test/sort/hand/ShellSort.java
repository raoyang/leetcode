package org.sunshine.lc.test.sort.hand;

public class ShellSort {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        sort(arr);
        System.out.print(arr);
    }

    private static void sort(int []arr){
        if(arr == null || arr.length < 2){
            return;
        }

        for(int gap=arr.length/2; gap>0; gap/=2){
            for(int i=gap; i<arr.length; i++){
                int temp = arr[i];
                int j; //j这个指针参考直接插入排序，用于反方向查找合适的位置
                for(j=i;j>=gap&&arr[j-gap]>temp;j-=gap){
                    arr[j] = arr[j-gap];
                }
                arr[j]=temp;
            }
        }
    }
}

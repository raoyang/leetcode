package org.sunshine.lc.test.sort.other;

public class MiddleFind {

    public static void main(String args[]){
        int arr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        System.out.print(getMiddle(arr, 16));
    }

    private static int getMiddle(int arr[], int target){
        int len = arr.length;
        int start = 0;
        int end = len - 1;
        int index = 0;
        while(start <= end){
            index = start + (end - start) / 2;
            if(arr[index] > target){
                end = index - 1;
            }else if(arr[index] < target){
                start = index + 1;
            }else{
                break;
            }
        }
        return index;
    }
}

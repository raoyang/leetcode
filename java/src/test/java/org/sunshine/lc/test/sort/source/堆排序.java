package org.sunshine.lc.test.sort.source;

public class 堆排序 {


    public static void main(String args[]){
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,16};
        int len = arr.length - 1;
        int beginIndex = (len - 1) / 2;
        for(int i = beginIndex ; i >= 0 ; i --){
            modifyHeap(arr, i, len);
        }

        for(int i = len ; i >= 0 ; i --){
            swap(arr, 0, i);
            modifyHeap(arr, 0, i - 1);
        }

        System.out.print(arr);
    }

    /***
     * 将堆的index节点以及其子树，满足堆的定义
     * @param arr
     */
    private static void modifyHeap(int arr[], int index, int len){

        int lChild = (index << 1) + 1;
        int lRight = lChild + 1;
        int maxChild = lChild;

        if(lChild > len)
            return;
        if(lRight <= len && arr[lRight] > arr[lChild]){
            maxChild = lRight;
        }

        if(arr[maxChild] > arr[index]){
            swap(arr, maxChild, index);
            modifyHeap(arr, maxChild, len);
        }
    }

    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

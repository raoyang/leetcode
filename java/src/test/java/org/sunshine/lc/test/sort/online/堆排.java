package org.sunshine.lc.test.sort.online;

public class 堆排 {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        int start = (arr.length / 2) - 1;
        //成为一个合法的堆
        for(int i = start ; i >= 0; i --){
            modifyHeap(arr, i, arr.length);
        }
        for(int i = arr.length - 1 ; i >= 0 ; i --){
            swap(arr, 0, i);
            modifyHeap(arr, 0, i - 1);
        }
        System.out.print(arr);
    }

    /***
     * 促使index这个节点及其子树，满足堆的定义
     * @param arr
     * @param index
     * @param len
     */
    private static void modifyHeap(int arr[], int index, int len){
        int lChild = (index << 1) + 1;
        if(lChild > len){
            return; //左子节点都超过len了，已经没有modify的意义了
        }
        int lRight = lChild + 1;
        int maxIndex = lChild;
        if(lRight <= len && arr[lRight] > arr[maxIndex]){
            maxIndex = lRight;
        }
        if(arr[index] < arr[maxIndex]){
            swap(arr, index, maxIndex);
            modifyHeap(arr, maxIndex, len);
        }
    }

    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

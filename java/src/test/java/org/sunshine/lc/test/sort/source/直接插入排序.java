package org.sunshine.lc.test.sort.source;

public class 直接插入排序 {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17,0};
        insertSort(arr);
        System.out.print(arr);
    }

    // 插入排序
    private static void insertSort(int arr[]){
        // 检查数据合法性
        if(arr == null || arr.length == 0){
            return;
        }
        int len = arr.length;
        for(int i = 1; i < len; i++){
            int tmp = arr[i];
            int j;
            for(j = i-1; j >= 0; j--){
                //如果比tmp大把值往后移动一位
                if(arr[j] > tmp){
                    arr[j+1] = arr[j];
                }
                else{
                    break;
                }
            }
            arr[j+1] = tmp;
        }
    }
}

package org.sunshine.lc.test.sort.source;

import java.util.Arrays;

public class 归并排序 {

    public static void main(String args[]){
        int[] arr = {16,12,11,10,5,3,2,6,8,1,4,7,9,17};
        int []temp = sort(arr);
        System.out.print(temp);
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

    private static int[] merge(int a[], int b[]){
        int arr[] = new int[a.length + b.length];
        int index_a = 0;
        int index_b = 0;

        int k = 0;
        while(index_a < a.length || index_b < b.length){
            //两个数组都没有拿完
            if(index_a < a.length && index_b < b.length){
                if(a[index_a] <= b[index_b]){
                    arr[k++] = a[index_a++];
                }else{
                    arr[k++] = b[index_b++];
                }
            } else if(index_b < b.length){
                arr[k++] = b[index_b++];
            } else if(index_a < a.length){
                arr[k++] = a[index_a++];
            } else{
                break;
            }
        }

        return arr;
    }
}

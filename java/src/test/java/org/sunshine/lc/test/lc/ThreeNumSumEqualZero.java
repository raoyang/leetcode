package org.sunshine.lc.test.lc;

import java.util.*;

/***
 * 三数之和等于0
 */
public class ThreeNumSumEqualZero {

    public static void main(String args[]){
        //int arr[] = {-4,-5,-6,-1,-2,0,3,5,6,7,3,1};
        int arr[] = {-2, 0 , 0 , 2 , 2};
        List<List<Integer>> result = getSumEqualZero(arr);
        System.out.print(result);
    }

    public static List<List<Integer>> getSumEqualZero(int []arr){
        if(arr == null || arr.length <= 2){
            return new ArrayList<>();
        }
        Arrays.sort(arr);
        int v1 = arr[0];
        int v2 = arr[arr.length - 1];
        if(v1<0 && v2<0){
            return new ArrayList<>();
        }
        if(v1>0 && v2>0){
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();

        int m , n;
        for(int i = 0 ; i < arr.length ; i ++){
            if(arr[i]>0){
                break;
            }
            if(i > 0 && arr[i] == arr[i-1]){
                continue;
            }
            m = i + 1;
            n = arr.length - 1;
            while(m < n){
                if(arr[i] + arr[m] + arr[n] > 0){
                    n--;
                }else if(arr[i] + arr[m] + arr[n] < 0){
                    m ++;
                }else{
                    List<Integer> f = new ArrayList<>();
                    f.add(arr[i]);
                    f.add(arr[m]);
                    f.add(arr[n]);
                    list.add(f);
                    while(m<n && arr[m]==arr[m+1]) m++;
                    while(m<n && arr[n]==arr[n-1]) n--;
                    n--;
                    m++;
                }
            }
        }
        return list;
    }
}

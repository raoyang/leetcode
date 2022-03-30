package org.sunshine.lc.test.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoNumSum {

    public static void main(String args[]){
        int arr[] = {3,4,7,2,3,5,5};
        List<Integer[]> result = getTwoNumSumIndex(arr, 10);
        System.out.print(result);
    }

    /***
     * 获取arr数组中，两数之和等于target的两数下标
     * @param arr
     * @param target
     * @return
     */
    public static List<Integer[]> getTwoNumSumIndex(int arr[], int target){
        List<Integer[]> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            int value = arr[i];
            if(map.containsKey(target-value)){
                result.add(new Integer[]{map.get(target-value), i});
            }
            map.put(value, i);
        }
        return result;
    }
}

package org.sunshine.lc.test.lc;

import java.util.HashSet;
import java.util.Set;

/***
 * 缺失的第一个整数
 */
public class FirstMissingPositive {

    public static void main(String args[]){
        int arr[] = {-1,-2,-60,40,43};
        int i = firstMissingPositive(arr);
        System.out.println(i);
    }

    private static int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0){
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int min_z = Integer.MAX_VALUE;
        for(int i : nums){
            set.add(i);
            if(i > max){
                max = i;
            }
            if(i < min){
                min = i;
            }
            if(i < min_z && i > 0){
                min_z = i;
            }
        }
        if(max <= 0){
            return 1;
        }
        if(min > 1){
            return 1; //缺失的最小正整数就是1
        }
        if(max > 0 && min_z > 1){
            return 1;
        }
        int i=0;
        int value = min_z + 1;
        while(i < nums.length){
            if(set.contains(value)){
                value ++;
            }else{
                return value;
            }
        }
        return max + 1;
    }
}

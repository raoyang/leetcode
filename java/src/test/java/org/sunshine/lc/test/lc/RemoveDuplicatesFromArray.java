package org.sunshine.lc.test.lc;

import java.util.Arrays;

/***
 * 从升序数组中删除重复的元素
 */
public class RemoveDuplicatesFromArray {

    public static void main(String args[]){
        int arr[] = {0,0,1,1,1,2,2,4,4,6,7,8};
        int a = removeDuplicates(arr);
        System.out.print(a);
    }

    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int p=0;
        int q=p+1;
        while(q<nums.length){
            if(nums[p] != nums[q]){
                nums[p+1] = nums[q];
                p++;
            }
            q++;
        }
        return p+1;
    }
}

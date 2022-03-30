package org.sunshine.lc.test.lc;

/***
 * 删除有序数组中重复的数字
 */
public class RemoveDuplicatesFromList {

    public static void main(String args[]){
        int arr[] = {1,1,2};
        int index = removeDuplicates(arr);
        System.out.println(arr);
    }

    /***
     * 删除数组中的重复项
     * 使用快慢指针
     * @param nums
     * @return
     */
    private static int removeDuplicates(int[] nums) {
        if(nums == null){
            return -1;
        }
        if(nums.length == 0){
            return 0;
        }
        int slow = 0;
        for(int i = 1 ; i < nums.length ; i ++){
            int fastValue = nums[i];
            if(fastValue == nums[slow]){
                continue;
            }
            nums[++slow] = fastValue;
        }
        return slow;
    }
}

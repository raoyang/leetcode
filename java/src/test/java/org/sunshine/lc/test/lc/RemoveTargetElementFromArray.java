package org.sunshine.lc.test.lc;

public class RemoveTargetElementFromArray {

    public static void main(String args[]){
        int arr[] = {1};
        int a = removeElement3(arr, 1);
        System.out.print(a);
    }

    /***
     * 弱鸡解法
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {

        if(nums == null || nums.length == 0){
            return 0;
        }
        int i = 0;
        for(; i < nums.length ; i ++){
            if(nums[i] == val){
                if(i == nums.length - 1){
                    break;
                }
                int j = i + 1;
                while(j < nums.length && nums[j] == val)
                    j++;
                if(j == nums.length){
                    break;
                }else{
                    swap(nums, i, j);
                }
            }
        }
        return i;
    }

    public static int removeElement2(int[] nums, int val) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int i = 0;
        for(int num : nums){
            if(num != val){
                nums[i ++] = num;
            }
        }
        return i;
    }

    public static int removeElement3(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = nums.length - 1;
        int k = nums.length;
        for (; i >= 0; i--) {
            int value = nums[i];
            if (value == val) {
                k = i;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (nums[j] == val) {
                    swap(nums, i, j);
                    k = i;
                    break;
                }
            }
        }
        return k;
    }

    public static void swap(int [] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

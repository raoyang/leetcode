package org.sunshine.lc.test.lc;

/***
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 */
public class SearchBeginAndEndIndexFromList {

    public static void main(String args[]){
        int arr[] = {5,7,7,8,8,10};
        int[] result = searchRange(arr, 5);
        System.out.println(result);
    }

    private static int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1,-1};
        }
        int left = getLeft(nums, 0, nums.length - 1, target);
        int right = getRight(nums, 0, nums.length - 1, target);
        return new int[]{left, right};
    }

    private static int getLeft(int[] nums, int start, int end, int target){
        if(start > end){
            return -1;
        }
        int middle = start + ((end-start)>>1);
        if(nums[middle] == target){
            if(middle == start){
                return middle;
            }else{
                if(nums[middle-1] != target){
                    return middle;
                }
            }
        }
        if(target <= nums[middle]){
            return getLeft(nums, start, middle - 1, target);
        }else{
            return getLeft(nums, middle + 1, end, target);
        }
    }

    private static int getRight(int[] nums, int start, int end, int target){
        if(start > end){
            return -1;
        }
        int middle = start + ((end-start)>>1);
        if(nums[middle] == target){
            if(middle == end){
                return middle;
            }else{
                if(nums[middle+1] != target){
                    return middle;
                }
            }
        }
        if(target >= nums[middle]){
            return getRight(nums, middle+1, end, target);
        }else{
            return getRight(nums, start, middle-1, target);
        }
    }
}

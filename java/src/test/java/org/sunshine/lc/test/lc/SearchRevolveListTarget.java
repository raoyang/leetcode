package org.sunshine.lc.test.lc;

/***
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 *
 */
public class SearchRevolveListTarget {

    public static void main(String args[]){
        int arr[] = {4,5,6,7,0,1,2};
        int index = search(arr, 0);
        System.out.println(index);
    }

    /***
     * 采用二分法进行查找
     * @param nums
     * @param target
     * @return
     */
    private static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left = nums[0];
        if(left == target){
            return 0;
        }
        return searchWithIndex(nums, 0, nums.length - 1, target);
    }

    private static int searchWithIndex(int[] nums, int begin, int end, int target){
        if(end < begin){
            return -1;
        }
        int middle = begin + ((end - begin + 1) >> 1);
        if(target == nums[middle]){
            return middle;
        }

        if(nums[middle] >= nums[begin]){
            if(target >= nums[begin] && target <= nums[middle]){
                return searchWithIndex(nums, begin, middle-1, target);
            }else{
                return searchWithIndex(nums, middle+1, end, target);
            }
        }else{
            if(nums[middle] <= nums[end]){
                if(target >= nums[middle] && target <= nums[end]){
                    return searchWithIndex(nums, middle+1, end, target);
                }else{
                    return searchWithIndex(nums, begin, middle-1, target);
                }
            }
        }
        return -1;
    }
}

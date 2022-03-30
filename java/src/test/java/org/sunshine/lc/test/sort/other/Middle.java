package org.sunshine.lc.test.sort.other;

public class Middle {

    public static void main(String args[]){
        int arr[] = {1,3,5,6};

        int index = searchInsert(arr, 0);
        System.out.println(index);
    }

    public static int searchInsert(int[] nums, int target) {
        if(nums==null || nums.length == 0){
            return 0;
        }
        return getIndex(nums, 0, nums.length - 1, target);
    }

    private static int getIndex(int[] nums, int start, int end, int target){
        if(start > end){
            return end + 1;
        }
        if(end-start == 1 && target>=nums[start] && target<=nums[end]){
            if(target==nums[start]){
                return start;
            }else{
                return end;
            }
        }
        int middle = start + ((end-start)>>1);
        if(nums[middle] == target){
            return middle;
        }
        if(target < nums[middle]){
            return getIndex(nums, start, middle-1, target);
        }else{
            return getIndex(nums, middle+1,end, target);
        }
    }
}

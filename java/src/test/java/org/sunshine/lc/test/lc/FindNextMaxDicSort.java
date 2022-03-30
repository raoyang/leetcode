package org.sunshine.lc.test.lc;

/***
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 */
public class FindNextMaxDicSort {

    public static void main(String args[]){
        int arr[] = {2,3,1};
        nextPermutation(arr);
        System.out.print(arr);
    }

    /***
     * 算法思路，从数组末位开始遍历
     * @param nums
     */
    public static void nextPermutation(int[] nums) {

        if(nums == null || nums.length < 2){
            return;
        }
        int end = nums.length - 1;
        int j = end;
        while(j>0){
            if(nums[j] > nums[j-1]){
                //从end找到j，由于是升序，因此找一个最小的并且大于nums[j-1]的位置，交换之
                if(nums[end] > nums[j-1]){
                    swap(nums, end, j-1);
                }else{
                    int k = end;
                    while(k>=j){
                        if(nums[k] > nums[j-1]){
                            swap(nums, k, j-1);
                            break;
                        }
                        k--;
                    }
                }
                sort(nums, j, nums.length - 1);
                break;
            }else{
                j--;
            }
        }

        //没找到
        if(j == 0){
            sort(nums, j, nums.length - 1);
        }
    }

    /***
     * 使用快速排序
     * @param arr
     * @param start
     * @param end
     */
    private static void sort(int arr[], int start, int end){
        if(start >= end){
            return;
        }
        int k = arr[start];
        int i = start; //头指针
        int j = end; //尾指针
        while(i < j){
            while(i < j && arr[j] >= k)
                j --;
            swap(arr, j, i);
            while(i < j && arr[i] < k)
                i ++;
            swap(arr, i, j);
        }
        sort(arr, start, i);
        sort(arr, i+1, end);
    }

    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

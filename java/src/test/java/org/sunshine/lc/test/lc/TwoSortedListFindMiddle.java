package org.sunshine.lc.test.lc;

/***
 * 两个正序数组，寻找
 * 要求时间复杂度为O(log(m_n))
 */
public class TwoSortedListFindMiddle {

    public static void main(String args[]){
        int arr1[] = new int[]{1,2,3};
        int arr2[] = new int[]{5,6,7,8,9,10,11};

        System.out.print(findMedianSortedArrays(arr1, arr2));
    }

    /***
     * 获取两个正序数组的中位数
     * @param a
     * @param b
     * @return
     */
    private static double getTwoArrayMiddle(int a[], int b[]){
        //从数组更短的开始找
        if(a.length > b.length){
            int []temp = a;
            a = b;
            b = temp;
        }
        //经过上述操作，a数组的长度小于等于b数组
        int leftCount = (a.length + b.length + 1) / 2;
        int left = 0;
        int right = a.length;
        while (left < right){
            int i = left + (right - left + 1) / 2; //在a数组上划了一刀，a有i个数字
            int j = leftCount - i; //b数字占有j个数字
            //如果a[i]大于b[j+1]， 则right后退到i-1位置，否则left到i+1位置
            if(a[i-1] > b[j]){
                right = i-1;
            }else{
                left = i;
            }
        }
        int i = left;
        int j = leftCount - i;
        if(left == a.length){ //a数组全拿了
            if((a.length + b.length) % 2 == 0){
                return (double) (b[j-1] + b[j-2]) / 2;
            }else{
                return b[j-1];
            }
        }else{

        }

        return 0;
    }


    /***
     * 标准解法
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        // 分割线左边的所有元素需要满足的个数 m + (n - m + 1) / 2;
        int totalLeft = (m + n + 1) / 2;

        // 在 nums1 的区间 [0, m] 里查找恰当的分割线，
        // 使得 nums1[i - 1] <= nums2[j] && nums2[j - 1] <= nums1[i]
        int left = 0;
        int right = m;

        while (left < right) {
            int i = left + (right - left + 1) / 2; //i的意义是：从短的数组取几个数字
            int j = totalLeft - i; //j的意义是：从长的数组取几个数字
            if (nums1[i - 1] > nums2[j]) {
                // 下一轮搜索的区间 [left, i - 1]
                right = i - 1;
            } else {
                // 下一轮搜索的区间 [i, right]
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;

        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

        if (((m + n) % 2) == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
        }
    }
}

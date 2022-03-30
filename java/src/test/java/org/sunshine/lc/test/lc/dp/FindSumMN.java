package org.sunshine.lc.test.lc.dp;

import java.util.ArrayList;
import java.util.List;

/***
 * 求数组中和为M的N个数
 */
public class FindSumMN {

    public static void main(String args[]){
        int arr[] = {1,2,3,4,5,6};
        int M = 21;
        int N = 6;

        /*
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        getSumEqualM(arr, ans, subList, 0, M, N);
        System.out.println(ans);*/
        int count = getSumEqualMMethod(arr, M, N);
        System.out.println(count);
    }

    /***
     * 求数组中和为M的N个数
     * @param arr 有序
     * @param M
     */
    private static void getSumEqualM(int arr[], List<List<Integer>> ans, List<Integer> subList, int start, int M, int N){
        if(N == 0){
            if(M == 0){
                ans.add(new ArrayList<>(subList));
            }
            return;
        }

        for(int i = start ; i < arr.length ; i ++){
            int val = arr[i];
            if(val * N > M){ //由于数组有序，因此后面不用判断了
                break;
            }
            subList.add(val);
            getSumEqualM(arr, ans, subList , i+1, M - val, N - 1);
            subList.remove(subList.size()-1);
        }
    }

    /***
     * 有多少种解法
     * 使用动态规划 推导不出递推公式
     * @return
     */
    private static int getSumEqualMMethod(int arr[], int M, int N) {

        return -1;
    }
}

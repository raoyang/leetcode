package org.sunshine.lc.test.lc;

import java.util.Arrays;

/***
 * 三数之和最接近目标值
 */
public class ThreeNumSumApproachTarget {

    public static void main(String args[]){
        int arr[] = {1,6,9,14,16,70};
        int target=81;
        System.out.print(getMostApproachTarget(arr, target));
    }

    public static int getMostApproachTarget(int arr[], int target){
        if(arr.length <= 2){
            throw new IllegalArgumentException("arr数量不符合规范");
        }
        if(arr.length == 3){
            return (arr[0] + arr[1] + arr[2]);
        }
        int dif = Math.abs((arr[0] + arr[1] + arr[2]) - target);
        int sum = arr[0] + arr[1] + arr[2];
        Arrays.sort(arr);
        for(int i = 0 ; i < arr.length ; i ++){
            int L = i + 1;
            int R = arr.length - 1;
            while(L < R){
                int tempSum = arr[i] + arr[L] + arr[R];
                if(tempSum == target){
                    return tempSum;
                }
                int tempDif = Math.abs(tempSum-target);
                if(tempDif < dif){
                    sum = tempSum;
                    dif = tempDif;
                }
                if(tempSum > target){
                    R --;
                }else if(tempSum < target){
                    L ++;
                }
            }
        }
        return sum;
    }
}

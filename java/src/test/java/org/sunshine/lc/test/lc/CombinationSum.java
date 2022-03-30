package org.sunshine.lc.test.lc;

import java.util.ArrayList;
import java.util.List;

/***
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 */
public class CombinationSum {

    public static void main(String args[]){

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {


        return null;
    }

    private static void combination(int [] candidates, List<List<Integer>> combinations, List<Integer> subList, int target){
        int sum = 0;
        for(int i : subList){
            sum += i;
        }
        if(sum == target){
            combinations.add(new ArrayList<>(subList));
            return;
        }
        if(sum > target){
            return;
        }



        for(int i = 0 ; i < candidates.length ; i ++){
            if(candidates[i] > target){
                return;
            }
            subList.add(candidates[i]);
            combination(candidates, combinations, subList, target);
            subList.remove(subList.size() - 1);
        }
    }
}

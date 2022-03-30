package org.sunshine.lc.test.lc.dp;

/***
 * 青蛙跳台阶问题
 * 问题：有一只青蛙，每次只能跳一个台阶或者两个台阶，问它要跳到11（也有可能其它阶）阶，有多少种跳法
 */
public class FrogJumpStairs {

    public static void main(String args[]){
        int x = getFrogJumpMethodDP(11);
        System.out.println(x);
    }

    /***
     * 采用递归法跳台阶
     * @param n
     * @return
     */
    private static int getFrogJumpMethod(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){ //二阶级可以跳一次，也可以跳两次，所以跳二阶总共有两种方式
            return 2;
        }
        return getFrogJumpMethod(n-1) + getFrogJumpMethod(n-2);
    }

    /***
     * 采用动态规划跳台阶
     * 我们使用dp[i]表示跳到i台阶，总共有多少种解法
     * 得到动态转换公式：dp[i]=dp[i-1] + dp[i-2]
     * 因此空间复杂度为O(i)
     * @param n
     * @return
     */
    private static int getFrogJumpMethodDP(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){ //二阶级可以跳一次，也可以跳两次，所以跳二阶总共有两种方式
            return 2;
        }
        int dp[] = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2; i < n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }

    /***
     * 依然可以将dp数组压缩成2个变量，从1维压缩成0维
     * 参考斐波那契数列
     */
}

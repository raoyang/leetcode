package org.sunshine.lc.test.lc.dp;

/***
 * 背包算法
 */
public class Backpack {

    /***
     * 01背包算法
     * 算法描述：一共有N件物品，从第i(i从1开始)件物品的重量为weight[i],价值为value[i]，在总重量不超过背包承载上线M的情况下，
     * 能够装入背包的最大价值是多少？
     * @param args
     */

    public static void main(String args[]){
        int weight[] = {0,1,2,3,4};
        int value[] = {0,2,3,4,5};

        int N = 4;
        int M = 6;
        int maxValue = getMaxValueWithZip(N, M, weight, value);
        System.out.println(maxValue);
    }

    /***
     * @param N : 物品总数量
     * @param M : 背包总重量
     * @param weight : 物品的重量
     * @param value : 物品的价值
     * 使用动态规划，动态规划数组dp[i][j]表示最大重量j的情况下，放前i个物品的最大价值
     * 分析动态转移方程
     * dp[i][j] = dp[i-1][j] --> 背包在放前i-1时，已经放不下了，因此放前i个物品的价值等于前i-1个物品
     * dp[i][j] = dp[i-1][j-weight[i]] + value[i] --> 第i个物品的重量是weight[i] 价值是value[i]
     * 归纳出方程： dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i])
     * @return
     */
    private static int getMaxValue(int N, int M, int weight[], int value[]){
        int dp[][] = new int[N+1][M+1];
        for(int i = 1 ; i <= N ; i ++){ //物品数量
            for(int j = 1 ; j <= M ; j ++){ //背包重量
                if(j < weight[i]){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j-weight[i]] + value[i];
                }
            }
        }
        return dp[N][M];
    }

    /***
     * 上述二维数组，空间复杂度为O(M*N)
     * 其实我们在计算的时候dp[i][j]的时候
     *    M 0   1   2   3   4   5   6   7   8   9   10
     * N--|---|---|---|---|---|---|---|---|---|---|----
     * 0--|-0-|-0-|-0-|-0-|-0-|-0-|-0-|-0-|-0-|-0-|-0-- -->放置物品数量=0，不管背包能放多少，价值都为0
     * 1--|-0-|---|---|---|---|---|---|---|---|---|----
     * 2--|-0-|---|---|---|---|---|---|---|---|---|----
     * 3--|-0-|---|---|---|---|---|---|---|---|---|----
     * 4--|-0-|---|---|---|---|---|---|---|---|---|----
     * 5--|-0-|---|---|---|---|---|---|---|---|---|----
     * 6--|-0-|---|---|---|---|---|---|---|---|---|----
     * 从这个图中，我们可以看出，计算dp[i][j] = max[dp[i-1][j], dp[i-1][j-weight[i]] + value[i])的时候，其实可以
     * 重复在横向数组即dp[j]上进行运算，不需要浪费二维空间，但是j的遍历顺序一定是倒序（不然会覆盖前面的内容，导致dp[j-weight[i]]被覆盖，下一轮计算则错误）
     * 自此，我们得出动态方程：dp[j] = max(dp[j], d[j-weight[i]]+value[i])
     * @param N
     * @param M
     * @param weight
     * @param value
     * @return
     */
    private static int getMaxValueWithZip(int N, int M, int weight[], int value[]){
        int dp[] = new int[M+1];
        for(int i = 1 ; i <=N ; i ++){
            for(int j = M; j>=1 ; j --){
                if(j<weight[i]){
                    dp[j] = dp[j];
                }else{
                    dp[j] = Math.max(dp[j], dp[j-weight[i]]+value[i]);
                }
            }
        }
        return dp[M];
    }

    //完全背包（有N种物品，每个物品都有无限个）
    /***
     * dp[i][j]:前i件物品总重量为j的情况下，最大的价值
     * dp[i][j] = max(dp[i-1][j], dp[i][j-weight[i]] + value[i])
     */

    //多重背包(有N种物品，每个物品有n[i]个)
}

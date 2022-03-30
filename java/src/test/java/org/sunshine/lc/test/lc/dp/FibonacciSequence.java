package org.sunshine.lc.test.lc.dp;

/***
 * 斐波拉契数列的解法
 */
public class FibonacciSequence {

    public static void main(String args[]){
        //System.out.println(getValueWithIndex(20));
        int arr[] = new int[41];
        int value = getValueWithDPZip(11);
        System.out.println(value);
    }

    /***
     * 方法一：回溯，采用递归
     * 在n非常大的时候，效率极低，原因是我们并没有记录每个比n小的下标生成的结果，
     * 比如n = 10  f(10) = f(9) + f(8) 其中f(9) = f(8) + f(7) 在这个过程中，f(8)被计算了两次，如果n较大，会有大量的f(i)的重复计算(i<n)
     * 因此推荐使用更加高效的动态规划
     * @return
     */
    private static int getValueWithIndex(int n){
        if(n <= 0){
            return 0;
        }
        if(n <= 2){
            return 1;
        }

        return getValueWithIndex(n-1) + getValueWithIndex(n-2);
    }

    /***
     * 方法二：动态规划（一维数组）
     * 使用dp数组，记录每次生成的结果，空间复杂度O(n)
     * @param n
     * @return
     */
    private static int getValueWithDP(int n, int dp[]){
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n ; i ++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }

    /***
     * 方法三：动态规划，将一维数组压缩，只使用两个变量，空间复杂度变为O(1)
     * 因为计算n其实只需要f(n-2)和f(n-1)，不需要保存那么多变量，因此用两个
     * 变量a和b记录
     * @param n
     * @return
     */
    private static int getValueWithDPZip(int n){
        int a = 1;
        int b = 1;
        for(int i = 2; i <= n/2 ; i ++){
            a = a+b;
            b = a+b;
        }
        if(n % 2 == 1){
            return a + b;
        }
        return b;
    }
}

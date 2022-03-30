package org.sunshine.lc.test.lc.dp;

/***
 * 正则表达式匹配
 *
 * 给你一个字s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 */
public class RegexMatch {

    public static void main(String args[]){
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s, p));
    }

    /***
     * 典型的动态规划题目，先定义dp数组
     * dp[i][j]:字符串的前i位，能被p的前j位匹配，则dp[i][j]=true
     * 判断情况如下： 1. 如果第j位是个.号，则dp[i][j] = dp[i-1][j-1] ---> 能不能匹配，取决于上一次能不能匹配
     *               2. 如果第j位是个字母，则dp[i][j] = dp[i-1][j-1] -> str[i] == p[j]
     *                                     dp[i][j] = false        -> str[i] != p[j]
     *               3. 如果第j位是个*号，则dp[i][j] = dp[i][j-2] or dp[i-1][j] ---> s[i] == p[j-1]
     *                                    dp[i][j] = dp[i][j-2]
     * @param str
     * @param p
     * @return
     */
    public static boolean isMatch(String str, String p) {
        boolean [][] dp = new boolean[str.length() + 1][p.length() + 1];
        dp[0][0] = true; //str和p都为空字符串
        for(int i = 0 ; i <= str.length() ; i ++){
            for(int j = 1 ; j <= p.length() ; j ++){
                if(p.charAt(j-1) == '*') {
                    if(matches(str, p, i, j-1)){
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }else{
                        dp[i][j] = dp[i][j-2];
                    }
                }else{
                    if(matches(str, p, i, j)){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }
        return dp[str.length()][p.length()];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}

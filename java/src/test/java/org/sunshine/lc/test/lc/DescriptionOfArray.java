package org.sunshine.lc.test.lc;

/***
 * 外观数列
 */
public class DescriptionOfArray {

    public static void main(String args[]){
        String value = countAndSay(4);
        System.out.println(value);
    }

    private static String countAndSay(int n) {
        String[] dp = new String[n+1];
        dp[0] = "1";
        dp[1] = "11";
        for (int i = 2; i <= n; i++) {
            String last = dp[i - 1];
            char[] chars = last.toCharArray();
            StringBuilder sb = new StringBuilder();
            int m = 0;
            int count = 0;
            Character c = null;
            while (m < chars.length) {
                char cur = chars[m];
                if (c != null && c != cur) {
                    sb.append(count)
                            .append(c);
                    c = cur;
                    count = 1;
                } else {
                    c = cur;
                    count++;
                }
                m++;
            }
            if(count > 0 && c != null){
                sb.append(count)
                        .append(c);
            }
            dp[i] = sb.toString();
        }
        return dp[n-1];
    }
}

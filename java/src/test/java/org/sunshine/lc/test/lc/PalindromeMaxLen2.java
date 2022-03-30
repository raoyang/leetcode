package org.sunshine.lc.test.lc;

public class PalindromeMaxLen2 {

    public static void main(String args[]){
        String a = "abvccvbaadasfa";
        System.out.print(getMaxPalindromeStr(a));
    }

    /***
     * 这种方法有唯一一个问题，就是字符串为ccc的时候，无法判断，如果修改，则又无法判断cccc这样的字符串
     * @param s
     * @return
     */
    private static String getMaxPalindromeStr(String s){
        if(s.length() < 2){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i ++){
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append('#');
        s = sb.toString();

        int maxLen = Integer.MIN_VALUE;
        String result = "";
        for(int i = 0 ; i < s.length() ; i ++){
            Character c_i = s.charAt(i);
            int m,n,len;
            m = i - 1;
            n = i + 1;
            len = 1;
            while(m >= 0 && n < s.length() && s.charAt(m) == s.charAt(n)){
                len += 2;
                m--;
                n++;
            }
            String temp = s.substring(m+1, n);
            String real = temp.replace("#", "");
            len = real.length();
            if(len > maxLen){
                maxLen = len;
                result = real;
            }
        }
        return result;
    }
}

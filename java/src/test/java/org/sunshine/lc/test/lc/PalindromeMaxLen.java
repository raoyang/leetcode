package org.sunshine.lc.test.lc;

public class PalindromeMaxLen {

    public static void main(String args[]){
        String a = "ccc";
        System.out.print(getMaxPalindromeStr(a));
    }

    /***
     * 这种方法有唯一一个问题，就是字符串为ccc的时候，无法判断，如果修改，则又无法判断cccc这样的字符串
     * @param str
     * @return
     */
    private static String getMaxPalindromeStr(String str){
        if(str.length() < 2){
            return str;
        }
        int maxLen = Integer.MIN_VALUE;
        String result = "";
        for(int i = 0 ; i < str.length() ; i ++){
            int next = i + 1;
            if(next >= str.length()){
                break;
            }
            Character c_i = str.charAt(i);
            Character c_next = str.charAt(next);
            int m,n,len;
            if(c_i.equals(c_next)){
                m = i - 1;
                n = next + 1;
                len = 2;
            }else{
                m = i - 1;
                n = i + 1;
                len = 1;
            }
            while(m >= 0 && n < str.length() && str.charAt(m) == str.charAt(n)){
                len += 2;
                m--;
                n++;
            }
            if(len > maxLen){
                maxLen = len;
                result = str.substring(m+1, n);
            }
        }
        return result;
    }
}

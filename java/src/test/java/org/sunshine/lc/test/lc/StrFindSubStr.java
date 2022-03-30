package org.sunshine.lc.test.lc;

/***
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 */
public class StrFindSubStr {

    public static void main(String args[]){
        String haystack = "mississippi";
        String needle = "issipi";
        System.out.print(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0){
            return 0;
        }
        if(haystack.length() < needle.length()){
            return -1;
        }
        char[] c1 = haystack.toCharArray();
        char[] c2 = needle.toCharArray();
        int i = 0;
        int j = c1.length - 1;
        while(i <= j){
            while(i < c1.length && c1[i] != c2[0])
                i ++;
            while(j >= 0 && c1[j] != c2[c2.length - 1])
                j--;
            if(i == c1.length || j == -1){
                return -1;
            }
            //1. 判断i这个启示位置是不是needle字符串，如果是：返回i，否则i++，继续循环
            int x = i;
            int k = 0;
            while(k < c2.length && x < c1.length && c1[x] == c2[k]){
                x++;
                k++;
            }
            if(k == c2.length){
                return i;
            }
            i ++;
        }

        return -1;
    }
}

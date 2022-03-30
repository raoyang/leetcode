package org.sunshine.lc.test.lc;

import java.util.Stack;

/***
 * 字符串转数字
 */
public class StrExchange2Int {

    public static void main(String args[]){
        String a = "-91283472332";
        int value = getIntValue(a);
        System.out.print(value);
    }

    private static int getIntValue(String str){
        int index = 0;
        while(index < str.length() && str.charAt(index) == ' '){
            index ++;
        }
        if(index == str.length()){
            return 0;
        }
        int sign = 1;
        if(str.charAt(index) == '+'){
            index ++;
        }else if(str.charAt(index) == '-'){
            sign = -1;
            index ++;
        }
        int result = 0;
        while(index < str.length()){
            char c = str.charAt(index);
            if(c > '9' || c < '0'){
                break;
            }
            if(sign == 1 && (Integer.MAX_VALUE - (c-'0'))/10 < result){
                return Integer.MAX_VALUE;
            }
            if(sign == -1 && (Integer.MIN_VALUE - sign*(c-'0'))/10> result){
                return Integer.MIN_VALUE;
            }
            result = result * 10 + sign * (c - '0');
            index ++;
        }
        return result;
    }
}

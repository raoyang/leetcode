package org.sunshine.lc.test.lc;

import java.util.*;

/***
 *给定一个仅包含数2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class TelNumLetterCombination {

    private static Map<Character, String> map = new HashMap<>();

    static{
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    public static void main(String args[]){
        String str = "2345678";
        List<String> list = letterCombinations(str);
        System.out.print(list);
    }

    public static List<String> letterCombinations(String str){
        if(str == null || str.isEmpty()){
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        if(str.length() == 1){
            Character c1 = str.charAt(0);
            String s1 = map.get(c1);
            for(int i = 0 ; i < s1.length() ; i ++){
                result.add(String.valueOf(s1.charAt(i)));
            }
            return result;
        }
        List<String> result1 = letterCombinations(str.substring(0, str.length() - 1));
        List<String> result2 = letterCombinations(String.valueOf(str.charAt(str.length() - 1)));
        List<String> result3 = new ArrayList<>();
        for(int i = 0 ; i < result1.size() ; i ++){
            for(int j = 0 ; j < result2.size() ; j ++){
                result3.add(result1.get(i) + result2.get(j));
            }
        }
        return result3;
    }
}

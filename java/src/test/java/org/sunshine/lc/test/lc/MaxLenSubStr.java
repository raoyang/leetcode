package org.sunshine.lc.test.lc;

import java.util.ArrayList;
import java.util.List;

/***
 * 无重复字符的最长字串
 */
public class MaxLenSubStr {

    public static void main(String args[]){
        String str = "pwwkew";
        System.out.print(getMaxLenSubStr(str));
    }

    /***
     * 采用滑动窗口算法计算
     * @param str
     * @return
     */
    private static String getMaxLenSubStr(String str){
        int i;
        int maxSize=0;
        List<Character> maxList = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        for(int j = 0 ; j < str.length(); j ++){
            Character character = str.charAt(j);
            if(list.contains(character)){
                i = list.indexOf(character) + 1;
                int size = list.size();
                if(size > maxSize){
                    maxSize = size;
                    maxList.clear();
                    maxList.addAll(list);
                }
                list = list.subList(i, list.size());
            }
            list.add(character);
        }
        if(list.size() > maxSize){
            maxList = list;
        }
        StringBuilder sb = new StringBuilder();
        for(Character c : maxList){
            sb.append(c);
        }
        return sb.toString();
    }
}

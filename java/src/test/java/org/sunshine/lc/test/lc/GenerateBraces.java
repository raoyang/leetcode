package org.sunshine.lc.test.lc;

import java.util.ArrayList;
import java.util.List;

/***
 * 生成括号
 */
public class GenerateBraces {

    public static void main(String args[]){
        System.out.print(generateBraces2(4));
    }

    /***
     * 生成括号 n-1 = (p) + q;
     * @param n
     * @return
     */
    public static List<String> generateBraces2(int n){
        if(n < 1){
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        if(n == 1){
            result.add("()");
            return result;
        }

        List<String> list = new ArrayList<>();

        for(int i = 0 ; i <= n - 1 ; i ++){
            List<String> p = generateBraces2(i);
            List<String> q = generateBraces2((n-1) - i);
            if(p.isEmpty()){
                for(String sq : q){
                    list.add("()" + sq); //p里面为空，
                }
            }else{
                for(String sp : p){
                    if(q.isEmpty()){
                        list.add("(" + sp + ")");
                    }else{
                        for(String sq : q){
                            list.add("(" + sp + ")" + sq);
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * 这个算法有问题，无法解决 这种括号的问题 (())(())
     * @param num
     * @return
     */
    public static List<String> generateBraces(int num){
        if(num < 1){
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        if(num == 1){
            result.add("()");
            return result;
        }

        List<String> list1 = generateBraces(num - 1);
        List<String> list = new ArrayList<>();
        for(String str1 : list1){
            String s1 = "()" + str1;
            String s2 = str1 + "()";
            list.add(s1);
            if(!s1.equals(s2)){
                list.add(s2);
            }
            list.add('(' + str1 + ')');
        }
        return list;
    }
}

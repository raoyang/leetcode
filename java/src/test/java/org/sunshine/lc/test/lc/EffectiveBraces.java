package org.sunshine.lc.test.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * 有效的括号
 */
public class EffectiveBraces {

    public static void main(String args[]){
        String str = "(){}}{";
        boolean isValid = isValid2(str);
        System.out.print(isValid);
    }

    /***
     * 递归的方式，还有一种栈的方式
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if(s.isEmpty()){
            return true;
        }
        if(s.length() < 2){
            return false;
        }
        if(s.length() == 2){
            if(s.startsWith("(") && s.endsWith(")")){
                return true;
            }else if(s.startsWith("[") && s.endsWith("]")){
                return true;
            }else if(s.startsWith("{") && s.endsWith("}")){
                return true;
            }else{
                return false;
            }
        }
        boolean reduce = false;
        for(int i = 0 ; i < s.length() - 1 ; i ++){
            int j = i + 1;
            if(j >= s.length()){
                break;
            }
            boolean match = false;
            if(s.charAt(i) == '(' && s.charAt(j) == ')'){
                match = true;
            }else if(s.charAt(i) == '[' && s.charAt(j) == ']'){
                match = true;
            }else if(s.charAt(i) == '{' && s.charAt(j) == '}'){
                match = true;
            }
            if(match){
                if(j == s.length() - 1){
                    s = s.substring(0, i);
                }else{
                    s = s.substring(0, i) + s.substring(j+1, s.length());
                }
                reduce = true;
            }
        }
        if(!reduce){
            return false;
        }else{
            return isValid(s);
        }
    }

    public static boolean isValid2(String s){
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for(Character character : s.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == map.get(character)){
                stack.pop();
            }else{
                stack.push(character);
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }
}

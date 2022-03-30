package org.sunshine.lc.test.lc;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/***
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class LongestValidParentheses {

    public static void main(String args[]){
        String s = "(()()))";
        int num = longestValidParentheses(s);
        System.out.println(num);
    }

    private static int longestValidParentheses(String s) {
        int max = 0;
        Stack<Position> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for(int i = 0 ; i < chars.length ; i ++){
            if(stack.isEmpty()){
                stack.push(new Position(i, chars[i]));
            }else{
                char cur = chars[i];
                if(stack.peek().ch == '(' && cur == ')'){
                    stack.pop();
                    if(stack.isEmpty()){
                        max = Math.max(max, i + 1);
                    }else{
                        max = Math.max(max, (i-stack.peek().index));
                    }
                }else{
                    stack.push(new Position(i, chars[i]));
                }
            }
        }
        return max;
    }

    private static class Position{
        private int index;
        private char ch;
        public Position(int index, char ch){
            this.index = index;
            this.ch = ch;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public char getCh() {
            return ch;
        }

        public void setCh(char ch) {
            this.ch = ch;
        }
    }

    public static int longestValidParenthesesCopy(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}

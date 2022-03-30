package org.sunshine.lc.test.lc;

import java.util.LinkedList;

/***
 * 整数反转
 */
public class IntReversal {

    public static void main(String args[]){
        int a = -1234567889;
        System.out.print(getReversal(a));
        //System.out.print((int)(9 * Math.pow(10, 9)));
        //System.out.print(Integer.MAX_VALUE);
    }

    private static int getReversal(int value){
        LinkedList<Integer> list = new LinkedList<>();
        int v = value;
        while(v != 0){
            int last = v % 10;
            list.addLast(last);
            v = v / 10;
        }
        int i = 0;
        int result = 0;
        while(!list.isEmpty()){
            double d = list.pollLast() * Math.pow(10, i);
            if(d > 0 && d > Integer.MAX_VALUE - result){
                return 0;
            }else if(d < 0 && d < Integer.MIN_VALUE - result){
                return 0;
            }
            result += d;
            i ++;
        }
        return result;
    }
}

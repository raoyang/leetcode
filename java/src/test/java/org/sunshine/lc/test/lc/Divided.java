package org.sunshine.lc.test.lc;

public class Divided {

    public static void main(String args[]){
        int dividend = 10000;
        int divisor = 3;

        System.out.println(Integer.MIN_VALUE);
        System.out.print(divide(dividend, divisor));
    }

    public static int divide(int dividend, int divisor) {

        if(dividend == 0){
            return 0;
        }
        if(divisor == -1){
            if(dividend > Integer.MIN_VALUE){
                return -dividend;
            }else{
                return Integer.MAX_VALUE;
            }
        }

        int flagA = dividend > 0 ? 1 : -1;
        int flagB = divisor > 0 ? 1 : -1;

        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);

        if(a < b){
            return 0;
        }
        int sum = 1;
        while(a >= b){
            long c = b << 1;
            if(c < a){
                sum = sum << 1;
                b = c;
            }else if(c == a){
                sum = sum << 1;
                return (sum * flagA * flagB);
            }else{
                break;
            }
        }

        int c = (int)(a-b);


        return sum * (flagA*flagB) + divide((flagA * c), divisor);
    }
}

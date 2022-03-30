package org.sunshine.lc.test.lc;

public class Pow {

    public static void main(String args[]){
        System.out.print(myPow(0.00001, 2147483647));
    }

    public static double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    /***
     * 假设计算2的37次幂，则计算2的32次 * 2的5次，递归计算
     * @param x
     * @param N
     * @return
     */
    private static double quickMul(double x, long N){
        if(N == 0){
            return 1;
        }
        if(N == 1){
            return x;
        }
        double res = x;
        long m = 1;
        while(m < N){
            long M = m << 1;
            if(M > N){
                break;
            }
            m = M;
            res *=res;
        }
        return res * quickMul(x, (N - m));
    }
}

// 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。

class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        // 转为long，避免溢出
        long b = n;
        if(n < 0){
            b = -b;
            x = 1 / x;
        }
        return getPow(x, b);
    }
    private double getPow(double x, long n){
        if(n == 0) return 1;
        if(n == 1) return x;
        double temp = getPow(x, n / 2);
        if(n % 2 == 1) {
            return x * temp * temp;
        }else{
            return temp * temp;
        }
    }
}
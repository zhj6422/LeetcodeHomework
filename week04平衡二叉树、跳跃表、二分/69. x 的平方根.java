// 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。

// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。



// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/sqrtx
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 从0到x中找到一个值mid，使得mid*mid <= x
class Solution {
    public int mySqrt(int x) {
        int left = 0, right = x;
        while(left < right){
            int mid = (left + right + 1) / 2;
            if(mid <= x / mid){
                left = mid;
            }else{
                right = mid - 1;
            }
        }
        return right;
    }
}
// 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 贪心：如果大于4，则把每段分割成3，这样乘积最大
class Solution {
    public int cuttingRope(int n) {
        long result = 1;
        if(n < 4) return n - 1;
        else if(n == 4) return 4;
        while(n > 4){
            result *= 3;
            result %= 1000000007;
            n -= 3;
        }
        result *= n;
        result %= 1000000007;
        return (int)result;
    }
}
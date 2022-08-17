// 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

//  

// 示例:

// 输入: n = 10
// 输出: 12
// 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
// 说明:  

// 1 是丑数。
// n 不超过1690。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/chou-shu-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


// 动态规划
// dp[i]表示低i+1个丑数
// 每个丑数都是之前丑数的2、3、5倍，dp[0] = 1，从这里开始取他们的最小值保存到下一位
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        for(int i = 1; i < n; i++){
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(n2, Math.min(n3, n5));
            if(dp[i] == n2) a++;
            if(dp[i] == n3) b++;
            if(dp[i] == n5) c++;
        }
        return dp[n - 1];
    }
}
// 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

class Solution {
    public int climbStairs(int n) {
        if(n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            // 到达当前阶梯的走法有从前面一个阶梯走一步和前两个阶梯走两步
            dp[i] = dp[i - 1] + dp[i - 2];
        } 
        return dp[n];
    }
}
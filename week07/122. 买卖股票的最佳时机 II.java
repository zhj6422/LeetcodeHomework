// 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

// 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

// 返回 你能获得的 最大 利润 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[1] 到 dp[n] 为第一天到第n天的情况
        int[][] dp = new int[n + 1][2];
        // 初始化，赋值为一个较小值，以及初始状态为0
        for(int i = 0; i <= n; i++){
            for(int j = 0; j < 2; j++) {
                dp[i][j] = -(int)1e9;
            }
        }
        dp[0][0] = 0;
        for(int i = 1; i <= n; i++){ // 从第一天遍历到第n天
            // price从0开始计数，所以要减一（0代表第一天）
            // “上一天买入状态下卖出”能否带来更大收益
            dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] + prices[i - 1]);
            // ”上一天卖出状态下买入“能否带来更大收益
            dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] - prices[i - 1]);
            for(int j = 0; j < 2; j++){
                // 保持原有状态的情况
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }
        // 返回第n天卖出状态下的值
        return dp[n][0];
    }
}
// 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​

// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 第一维表示第几天，第二维表示持有还是不持有股票，第三维表示是否在冷冻期
        int[][][] dp = new int[n + 1][2][2];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j < 2; j++){
                for(int l = 0; l < 2; l++){
                    dp[i][j][l] = -(int)1e9;
                }
            }
        }
        dp[0][0][0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < 2; j++){
                for(int l = 0; l < 2; l++){
                    dp[i][1][0] = Math.max(dp[i][1][0], dp[i - 1][0][0] - prices[i - 1]);
                    dp[i][0][1] = Math.max(dp[i][0][1], dp[i - 1][1][0] + prices[i - 1]);
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][j][l]);
                }
            }
        }
        return Math.max(dp[n][0][1], dp[n][0][0]);
    }
}
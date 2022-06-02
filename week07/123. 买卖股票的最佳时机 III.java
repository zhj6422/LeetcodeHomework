// 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n+1][2][3];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 3; k++){
                    dp[i][j][k] = -(int)1e9;
                }
            }
        }
        dp[0][0][0] = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 3; k++){
                    if(j == 0 && k < 2) dp[i+1][1][k+1] = Math.max(dp[i+1][1][k+1], dp[i][j][k] - prices[i]);
                    if(j == 1) dp[i+1][0][k] = Math.max(dp[i+1][0][k], dp[i][j][k] + prices[i]);
                    dp[i+1][j][k] = Math.max(dp[i+1][j][k], dp[i][j][k]);
                }
            }
        }
        int result = dp[n][0][0];
        for(int k = 1; k < 3; k++){
            result = Math.max(result, dp[n][0][k]);
        }
        return result;
    }
}
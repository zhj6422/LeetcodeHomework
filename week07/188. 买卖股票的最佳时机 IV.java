// 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。

// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 相比较于122题，多了限定最多进行k次交易
// dp数组多一个维度，来定义这次交易是第几次交易
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // dp数组三个维度，第一个表示现在第几天，第二个表示不持有股票（0）还是持有股票（1），第三个维度表示第几次交易
        int[][][] dp = new int[n+1][2][k+1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j < 2; j++){
                for(int c = 0; c <= k; c++){
                    // 赋初值，给一个较小值
                    dp[i][j][c] = -(int)1e9;
                }
            }
        }
        dp[0][0][0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < 2; j++){
                for(int c = 0; c <= k; c++){
                    if(c > 0){
                        dp[i][1][c] = Math.max(dp[i][1][c], dp[i - 1][0][c - 1] - prices[i - 1]);
                    }
                    dp[i][0][c] = Math.max(dp[i][0][c], dp[i - 1][1][c] + prices[i - 1]);
                    dp[i][j][c] = Math.max(dp[i - 1][j][c], dp[i][j][c]);
                }
            }
        }
        int result = dp[n][0][0];
        for(int i = 1; i <= k; i++){
            result = Math.max(result, dp[n][0][i]);
        }
        return result;
    }
}
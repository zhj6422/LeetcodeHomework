// 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 第一维：天数；第二维：是否买入；第三维：买入次数（本题只允许买入一次）
        int[][][] dp = new int[n + 1][2][2];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    dp[i][j][k] = -(int)1e9;
                } 
            }
        } 
        dp[0][0][0] = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    if(k == 0 && j == 0) dp[i+1][1][1] = Math.max(dp[i+1][1][1], dp[i][0][0] - prices[i]);
                    if(k == 0 && j == 1) dp[i+1][0][1] = Math.max(dp[i+1][0][1], dp[i][1][1] + prices[i]);
                    dp[i+1][j][k] = Math.max(dp[i+1][j][k], dp[i][j][k]);
                }
                
            }
        }
        // 如果没有赚，就返回0
        return Math.max(dp[n][0][1], 0);
    }
}
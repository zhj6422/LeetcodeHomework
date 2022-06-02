// 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。

// 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

// 返回获得利润的最大值。

// 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j < 2; j++){
                dp[i][j] = -(int)1e9;
            }
        }
        dp[0][0] = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                if(j == 0) dp[i+1][1] = Math.max(dp[i+1][1], dp[i][0] - prices[i] - fee);
                if(j == 1) dp[i+1][0] = Math.max(dp[i+1][0], dp[i][1] + prices[i]);
                dp[i+1][j] = Math.max(dp[i][j], dp[i+1][j]);
            }
        }
        // for(int i = 1; i <= n; i++){
        //     for(int j = 0; j < 2; j++){
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return Math.max(dp[n][0], dp[n][1]);
    }
}
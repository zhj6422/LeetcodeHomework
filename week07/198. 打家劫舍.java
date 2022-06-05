// 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/house-robber
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n+1][2];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j < 2; j++){
                dp[i][j] = -(int)1e9;
            }
        }
        dp[0][0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < 2; j++){
                dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
                dp[i][1] = dp[i - 1][0] + nums[i - 1];
            }
        }
        return Math.max(dp[n][0], dp[n][1]);
    }
}
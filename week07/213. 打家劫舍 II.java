// 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/house-robber-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 通过两次动规，第一次不偷1，第二次不偷n，然后取两者的较大者，就是1和n不会被同时偷盗的最佳结果

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n <= 1){
            return nums[0];
        }
        int[][] dp = new int[n+1][2];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j < 2; j++){
                dp[i][j] = -(int)1e9;
            }
        }
        // 不偷1：
        dp[1][0] = 0;
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < 2; j++){
                dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
                dp[i][1] = dp[i - 1][0] + nums[i - 1];
            }
        }
        int result = Math.max(dp[n][1], dp[n][0]);

        // 不偷n：
        dp[1][0] = 0;
        dp[1][1] = nums[0];
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < 2; j++){
                dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
                dp[i][1] = dp[i - 1][0] + nums[i - 1];
            }
        }
        // 和偷1不偷n的情况做对比取较大者
        result = Math.max(result, dp[n][0]);
        return result;

    }
}
// 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。

// 求所能获得硬币的最大数量。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/burst-balloons
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] nums1 = new int[n + 2];
        for(int i = 0; i < n; i++){
            nums1[i + 1] = nums[i];
        }
        nums1[0] = 1;
        nums1[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];
        for(int len = 1; len <= n; len++){
            for(int l = 1; l <= n - len + 1; l++){
                int r = l + len - 1;
                for(int p = l; p <= r; p++){
                    dp[l][r] = Math.max(dp[l][r], dp[l][p - 1] + dp[p + 1][r] + nums1[p] * nums1[l - 1] * nums1[r + 1]);
                }
            }
        }
        return dp[1][n];
    }
}
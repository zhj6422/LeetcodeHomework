// 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            sum += nums[i];
        }
        // 结果不可能包含0.5
        if(sum % 2 == 1) return false;
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;
        for(int i = 1; i <= n; i++){
            for(int j = sum / 2; j >= nums[i - 1]; j--){
                dp[j] = dp[j - nums[i - 1]] || dp[j];
            }
        }
        return dp[sum / 2];
    }
}
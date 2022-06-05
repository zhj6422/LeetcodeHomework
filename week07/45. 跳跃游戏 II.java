// 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。

// 数组中的每个元素代表你在该位置可以跳跃的最大长度。

// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。

// 假设你总是可以到达数组的最后一个位置。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/jump-game-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for(int i = 0; i < n; i++){
            dp[i] = (int)1e9;
        }
        dp[0] = 0;
        for(int i = 0; i < n; i++){
            for(int j = 1; j <= nums[i]; j++){
                if(i + j < n){
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }  
            }
        }
        return dp[n - 1];
    }
}
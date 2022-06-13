// 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

// 子数组 是数组中的一个连续部分。


class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        int[] preMin = new int[n + 1];
        s[0] = 0;
        for(int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        preMin[0] = s[0];
        for(int i = 1; i <= n; i++) preMin[i] = Math.min(preMin[i - 1], s[i]);
        int result = -(int)1e9;
        for(int i = 1; i <= n; i++){
            result = Math.max(result, s[i] - preMin[i - 1]);
        }
        return result;
    }
}
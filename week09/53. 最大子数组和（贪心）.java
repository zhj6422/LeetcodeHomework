// 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

// 子数组 是数组中的一个连续部分。

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int result = -(int)1e9;
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            result = Math.max(sum, result);
            // 如果和已经小于0，前面这一部分全部不要了
            if(sum < 0) sum = 0;
        }
        return result;
    }
}
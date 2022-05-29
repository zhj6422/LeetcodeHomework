// 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

// 测试用例的答案是一个 32-位 整数。

// 子数组 是数组的连续子序列。

//  

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/maximum-product-subarray
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 使用动规维护一个max和min数组，保存最大乘积和最小乘积
// 因为有正负数的原因，每次取 max[i - 1] * nums[i], min[i - 1] * nums[i], nums[i] 这三个里的最大值放入max，最小值放入min
// 最后遍历max数组，找出最大的乘积
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length; 
        int[] max = new int[n];
        int[] min = new int[n];
        max[0] = nums[0];
        min[0] = nums[0];
        for(int i = 1; i < n; i++){
            max[i] = Math.max(Math.max(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
            min[i] = Math.min(Math.min(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
        }
        int result = max[0];
        for(int i = 0; i < n; i++) result = Math.max(max[i], result);
        return result;
    }
}
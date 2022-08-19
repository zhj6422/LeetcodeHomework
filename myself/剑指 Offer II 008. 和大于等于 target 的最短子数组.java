// 给定一个含有 n 个正整数的数组和一个正整数 target 。

// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。

//  

// 示例 1：

// 输入：target = 7, nums = [2,3,1,2,4,3]
// 输出：2
// 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 示例 2：

// 输入：target = 4, nums = [1,4,4]
// 输出：1
// 示例 3：

// 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
// 输出：0

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/2VG8Kg
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


/*
 * 滑动窗口模版题
 * 关键词：连续子数组、连续子区间
 * 固定一边，收缩另一边
 * 求长度最小的连续区间：固定右边，根据条件缩小左边
 * 求长度最大的连续区间：固定左边，根据条件扩展右边
 * 在扩展或者收缩区间的时候，不断更新结果
 */

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        for(int right = 0; right < nums.length; right++){
            sum += nums[right];
            while(sum >= target){
                res = Math.min(res, right - left + 1);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
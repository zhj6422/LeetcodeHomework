// 给你一个整数数组 nums 和一个整数 k。如果某个连续子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。

// 请返回这个数组中 「优美子数组」 的数目。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/count-number-of-nice-subarrays
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 前缀和：求nums[i] % 2 的前缀和
// k个奇数 ==》 前缀和只差为k
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        // s为前缀和数组
        int[] s = new int[n + 1];
        for(int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1] % 2;
        // count数组，下标表示前缀和，数据表示该前缀和对应的个数
        int[] count = new int[n + 1];
        count[s[0]]++;
        int result = 0;
        for(int i = 1; i <= n; i++){
            // 加上前缀和为s[i] - k的个数
            if(s[i] - k >= 0)result += count[s[i] - k];
            // 维护count数组，前缀和为s[i]的这种情况个数加一
            count[s[i]]++;
        }
        return result;
    }
}
// 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。

//  

// 示例 1：

// 输入:nums = [1,1,1], k = 2
// 输出: 2
// 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
// 示例 2：

// 输入:nums = [1,2,3], k = 3
// 输出: 2

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/QTMn0o
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


/*
 * 前缀和思想
 * 处理区间，而又无法确定怎么移动左右两侧的情况（可以确定的话用滑动窗口）
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        // 存储 前缀和为sum 有多少个，key为sum，value为次数
        HashMap<Integer, Integer> map = new HashMap<>();
        // 初始情况，0出现1次
        map.put(0, 1);
        int sum = 0;
        int res = 0;
        for(int i : nums){
            sum += i;
            // 如果存在sum-k的前缀和，结果就加上它出现的次数（此时前面多次可以到这里匹配上）
            if(map.containsKey(sum - k)) res += map.get(sum - k);
            // 将当前前缀和 更新到map中
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
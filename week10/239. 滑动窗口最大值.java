// 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

// 返回 滑动窗口中的最大值 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/sliding-window-maximum
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 存放数据，key是nums的值，value是出现的次数
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int n = nums.length;
        Integer index;
        // 将前k个数放入TreeMap
        for(int i = 0; i < k; i++) {
            if((index = treeMap.get(nums[i])) == null) treeMap.put(nums[i], 1);
            else treeMap.put(nums[i], index + 1);
        }
        int[] result = new int[n - k + 1];
        // 获得第一组的最大值
        result[0] = treeMap.descendingKeySet().first();
        for(int i = 0; i + k < n; i++) {
            // 删除第i个，加入第i+k个，相当于移动窗口
            if((index = treeMap.get(nums[i])) > 1) treeMap.put(nums[i], index - 1);
            else treeMap.remove(nums[i]);
            if((index = treeMap.get(nums[i + k])) == null) treeMap.put(nums[i + k], 1);
            else treeMap.put(nums[i + k], index + 1);
            // 每次移动后获取最大值加入结果数组
            result[i + 1] = treeMap.descendingKeySet().first();
        }
        return result;
    }
}
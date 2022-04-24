// 给定一个非空且只包含非负数的整数数组 nums，数组的 度 的定义是指数组里任一元素出现频数的最大值。

// 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/degree-of-an-array
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int findShortestSubArray(int[] nums) {
        int maxCount = 0;
        int minWindow = 0;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 用于存放第一次出现的位置以及出现的次数
            int[] indexAndCount = map.get(nums[i]);
            if (indexAndCount == null) {
                // 第一次出现的时候，存入
                indexAndCount = new int[]{i, 1};
                map.put(nums[i], indexAndCount);
            } else { // 累计出现次数
                indexAndCount[1]++;
            }
            // 如果超过最大的频次，判断是否要更新结果
            if (indexAndCount[1] > maxCount) {
                maxCount = indexAndCount[1];
                minWindow = i - indexAndCount[0] + 1;
            } else if (indexAndCount[1] == maxCount) {
                minWindow = Math.min(minWindow, i - indexAndCount[0] + 1);
            }
        }
    
        // for(Map.Entry<Integer, int[]> entry : map.entrySet()){
        //     System.out.println(entry.getKey() + ": {" + entry.getValue()[0] + "," + entry.getValue()[1] + "}");
        // }
        return minWindow; 
    }
}
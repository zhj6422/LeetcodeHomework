// 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。

// 数组中的每个元素代表你在该位置可以跳跃的最大长度。

// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。

// 假设你总是可以到达数组的最后一个位置。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/jump-game-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int jump(int[] nums) {
        int result = 0; 
        int now = 0;
        while(now < nums.length - 1){
            // 当前能到达的最远坐标
            int right = now + nums[now];
            // 如果已经包含终点，返回结果
            if(right >= nums.length - 1) return result + 1;
            // 寻找下一个跳跃位置，找在now和right之间能跳最远的坐标
            int next = now;
            int nextRight = right;
            for(int i = now + 1; i <= right; i++){
                if(i + nums[i] > nextRight){
                    nextRight = i + nums[i];
                    next = i;
                }
            }
            now = next;
            result++;
        }
        return result;
    }
}
// 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

// 返回 滑动窗口中的最大值 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/sliding-window-maximum
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 使用优先队列实现
// 懒删除
class Solution {
    private PriorityQueue<int[]> pq;
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        pq = new PriorityQueue<>(nums.length, (a, b) -> b[0] - a[0]);
        for(int i = 0; i < nums.length; i++){
            // 保存一个数组，0位保存数值，1位保存下标
            pq.add(new int[]{nums[i], i});
            if(i >= k - 1){
                // 懒删除，优先队列取出的数据，检查是否超过窗口范围，是的话删除，继续从优先队列中取数据
                while(pq.peek()[1] <= i - k) pq.poll();
                // 找到满足窗口范围的才加入结果数组
                result[i - k + 1] = pq.peek()[0];
            }
        }
        return result;
    }
}
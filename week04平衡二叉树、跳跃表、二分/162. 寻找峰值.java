// 峰值元素是指其值严格大于左右相邻值的元素。

// 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

// 你可以假设 nums[-1] = nums[n] = -∞ 。

// 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/find-peak-element
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 实数三分，找峰值，取中间两个点，根据两点的大小情况可以去除三部分中的一部分
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right){
            // 分成三部分，left-leftMid, leftMid-rightMid, rightMid-right
            int leftMid = (left + right) / 2, rightMid = leftMid + 1;
            if(nums[leftMid] - nums[rightMid] > 0){ // 因为单调，如果leftMid > rightMid，可以抛弃rightMid右侧部分
                right = rightMid - 1;
            }else{ // 抛弃leftMid左侧部分
                left = leftMid + 1;
            }
        }
        return right;
    }
}
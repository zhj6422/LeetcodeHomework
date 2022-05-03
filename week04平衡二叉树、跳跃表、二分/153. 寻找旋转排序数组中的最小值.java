// 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
// 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

// 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

// 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 查找最小值，其实就是查两段单调排序数组的分界点
// 分界点位置：该处左边比数组最后一个元素要大，该处右边比最后一个元素要小
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            // 和最后一个元素做比较
            if(nums[mid] <= nums[nums.length - 1]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return nums[right];
    }
}
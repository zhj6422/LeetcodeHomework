// 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

// 如果数组中不存在目标值 target，返回 [-1, -1]。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        // 找到 >= target的第一个数
        // 从0-n
        int left = 0, right = nums.length;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] >= target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        result[0] = right;

        // 找到 <= target的最后一个数
        // 从-1到n-1
        left = -1;
        right = nums.length - 1;
        while(left < right){
            int mid = (left + right + 1) / 2;
            if(nums[mid] <= target){
                left = mid;
            }else{
                right = mid - 1;
            }
        }
        result[1] = right;

        if(result[0] > result[1]) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        return result;
    }
}
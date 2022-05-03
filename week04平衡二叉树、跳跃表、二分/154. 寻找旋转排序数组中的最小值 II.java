// 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
// 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

// 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

// 你必须尽可能减少整个过程的操作步骤。

//  

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 与153题不同：数据有重复，可能出现的情况：最后一个元素和最开始的元素可能相同，不能直接和最后一个元素比较
// 思路1:去掉最后和第一个元素重复的元素
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        while(nums[n - 1] >= nums[0] && n - 1 > left){
            n--;
        }
        int right = n - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] <= nums[n - 1]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return nums[right];
    }
}

// 思路2: 和right比较，如果出现重复，right--
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] > nums[right]){
                left = mid + 1;
            }else if(nums[mid] < nums[right]){
                right = mid;
            }else{
                right--;
            }
        }
        return nums[right];
    }
}
// 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。

//  

// 示例 1:

// 输入: nums = [0,1,0,3,12]
// 输出: [1,3,12,0,0]
// 示例 2:

// 输入: nums = [0]
// 输出: [0]
//  

// 提示:

// 1 <= nums.length <= 104
// -231 <= nums[i] <= 231 - 1


// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/move-zeroes
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 两次遍历
// 第一次遍历把非0元素都拉到最前面
// 第二次遍历把后面元素全部变成0
class Solution {
    public void moveZeroes(int[] nums) {
        int n = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[n] = nums[i];
                n++;
            }
        }
        for(int i = n; i < nums.length; i++){
            nums[i] = 0;
        }
    }
}



// 一次遍历
// 遍历数组，遇到不等于0的时候考虑交换
class Solution {
    public void moveZeroes(int[] nums) {
        if(nums.length == 0){
            return;
        }
        int n = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                if(i > n){ // 去除了最开始n=i交换的情况
                    nums[n] = nums[i];
                    nums[i] = 0;
                }
                n++;
            }
        }
    }
}









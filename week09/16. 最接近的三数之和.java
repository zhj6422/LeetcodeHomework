// 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。

// 返回这三个数的和。

// 假定每组输入只存在恰好一个解。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/3sum-closest
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int n;
    public int threeSumClosest(int[] nums, int target) {
        n = nums.length;
        Arrays.sort(nums);
        int result = (int)1e9;
        for(int i = 0; i < n - 2; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int k = n - 1;
            for(int j = i + 1; j < n - 1; j++){
                if(j > i + 1 && nums[j] == nums[j - 1]) continue;
                while(j < k && nums[j] + nums[k] + nums[i] > target) k--;
                if(j < k){
                    if(Math.abs(target - nums[i] - nums[j] - nums[k]) < Math.abs(result - target)){
                        result = nums[i] + nums[j] + nums[k];
                    } 
                    if(k < n - 1 && Math.abs(target - result) > Math.abs(target - nums[i] - nums[j] - nums[k+1]) ){
                        result = nums[i] + nums[j] + nums[k + 1];
                    } 

                }else{ // k == j
                    if(Math.abs(target - result) > Math.abs(target - nums[i] - nums[j] - nums[k+1])){
                        result = nums[i] + nums[j] + nums[k + 1];
                    }
                }
            }
        }
        return result;
    }
}
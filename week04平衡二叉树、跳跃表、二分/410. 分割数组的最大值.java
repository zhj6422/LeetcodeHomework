// 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。

// 设计一个算法使得这 m 个子数组各自和的最大值最小。

// 二分
class Solution {
    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for(int num: nums){
            right += num;
            left = Math.max(left, num);
        }
        while(left < right){
            int mid = (left + right) / 2;
            if(isOK(nums, m, mid)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
    // 用来判断每个box容纳size大小的数是否可行
    private boolean isOK(int[] nums, int m, int size){
        int box = 1;
        int curr = 0;
        for(int i = 0; i < nums.length; i++){
            if(curr + nums[i] <= size){
                curr += nums[i];
            }else{
                box += 1;
                if(box > m) return false;
                curr = nums[i];
            }
        }
        if(box <= m){
            return true;
        }
        return false;
    }
}
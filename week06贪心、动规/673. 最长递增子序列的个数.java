// 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。

// 注意 这个数列必须是 严格 递增的。

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // numsDp[i]表示到nums[0]到nums[i]的最长递增子序列长度
        int[] numsDp = new int[n];
        // resultsDp[i]表示以 nums[i]nums[i] 结尾的最长递增子序列的个数
        int[] resultsDp = new int[n];
        int max = 1;
        for(int i = 0; i < n; i++){
            numsDp[i] = 1;
            resultsDp[i] = 1;
        } 
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){ // 当前比j要小，存在递增情况
                    if(numsDp[i] < numsDp[j] + 1){ // 长度比之前第j位+1要短，更新位之前的数据
                        numsDp[i] = numsDp[j] + 1;
                        resultsDp[i] = resultsDp[j];
                    }else if(numsDp[i] == numsDp[j] + 1){ // 找到符合前驱的新方案，累加方案数
                        resultsDp[i] += resultsDp[j];
                    }
                }
            }
            max = Math.max(numsDp[i], max);
        }
        int result = 0;
        for(int i = 0; i < n; i++){
            if(numsDp[i] == max) result += resultsDp[i];
        }
        return result;
    }
}
// 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

// 注意：答案中不可以包含重复的三元组。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/3sum
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int n;
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        n = nums.length;
        Arrays.sort(nums);
        
        for(int i = 0; i < n; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            ArrayList<ArrayList<Integer>> temp = twoSum(nums, i + 1, -nums[i]);
            for(ArrayList<Integer> tempArray: temp){
                tempArray.add(nums[i]);
                result.add(tempArray);
            }
        }
        return result;
    }
    private ArrayList<ArrayList<Integer>> twoSum(int[] nums, int start, int target){
        int j = n - 1;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i = start; i < n; i++){
            if(i > start && nums[i] == nums[i - 1]) continue;
            while(i < j && nums[i] + nums[j] > target) j--;
            if(i < j && nums[i] + nums[j] == target){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(nums[i]);
                temp.add(nums[j]);
                result.add(temp);
            }
        }
        return result;
    }
}
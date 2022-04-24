// 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int[] used1 = new int[9]; // nums数组长度为1-8，used1用来存储该位置是否被用过
        result.clear();
        path.clear();
        backTracking(nums, used1);
        return result;
    }

    private void backTracking(int[] nums, int[] used1){
        // 如果达到对应的长度，就存入结果
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        int[] used2 = new int[21]; // nums[i]处于-10到10之间，used2用来存放某个数是否被使用
        for(int i = 0; i < nums.length; i++){
            if(used1[i] == 1 || used2[nums[i]+10] == 1){
                continue;
            }
            // 存入中间结果，并处理两个used数组
            path.add(nums[i]);
            used1[i] = 1;
            used2[nums[i]+10] = 1;
            // 递归方法
            backTracking(nums, used1);
            // 清理现场
            path.remove(path.size() - 1);
            used1[i] = 0;
        }
    }
    
}
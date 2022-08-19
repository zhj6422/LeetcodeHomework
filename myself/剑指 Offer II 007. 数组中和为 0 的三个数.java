// 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。

//  

// 示例 1：

// 输入：nums = [-1,0,1,2,-1,-4]
// 输出：[[-1,-1,2],[-1,0,1]]
// 示例 2：

// 输入：nums = []
// 输出：[]
// 示例 3：

// 输入：nums = [0]
// 输出：[]

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/1fGaJU
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        for(int i = 0; i < n - 2; i++){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int temp = nums[i] * -1;
            int left = i + 1, right = n - 1;
            while(left < right){
                if(nums[left] + nums[right] == temp){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 只需要在保存的时候进行重复判断，这里肯定会修改left，后面right肯定不符合要求，不用再判断right是否重复，跳过重复的right
                    while(left < right && nums[left + 1] == nums[left]){
                        // 过滤重复部分
                        left++;
                    }
                    // 下一位不重复
                    left++;
                }else if(nums[left] + nums[right] > temp){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return result;
    }
}

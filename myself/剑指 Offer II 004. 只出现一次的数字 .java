// 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。

//  

// 示例 1：

// 输入：nums = [2,2,3,2]
// 输出：3
// 示例 2：

// 输入：nums = [0,1,0,1,0,1,100]
// 输出：100

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/WGki4K
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


/*
 * 存储在map里面，每次存进去的时候减1，减到0了就删掉
 * 最后剩下的一个就是结果
 */
class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
    public int singleNumber(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            Integer temp = map.get(nums[i]);
            if(temp != null){
                if(temp == 1) map.remove(nums[i]);
                else map.put(nums[i], temp - 1);
            }else{
                map.put(nums[i], 2);
            }
        }
        for(int i : map.keySet()){
            return i;
        }
        return -1;
    }
}


/*
 * 位运算
 * 新建一个数组，32位，保存每个数32位各个位等于1的个数（cnt[0] = 40，表示第0位有40个数为1）
 * 对出现的数都按照二进制展开，根据展开后的情况填补cnt数组
 * 对cnt数组的每一位对3取模，剩下的如果为1的位组成的这个数，就是只出现1次的数（模3将出现3次的数全部淘汰掉了）
 */
class Solution {
    public int singleNumber(int[] nums) {
        int[] cnt = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if (((x >> i) & 1) == 1) {
                    cnt[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((cnt[i] % 3 & 1) == 1) {
                ans += (1 << i);
            }
        }
        return ans;
    }
}

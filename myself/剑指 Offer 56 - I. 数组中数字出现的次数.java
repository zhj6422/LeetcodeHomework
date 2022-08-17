// 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

//  

// 示例 1：

// 输入：nums = [4,1,4,6]
// 输出：[1,6] 或 [6,1]
// 示例 2：

// 输入：nums = [1,2,10,4,1,4,3,3]
// 输出：[2,10] 或 [10,2]
//  

// 限制：

// 2 <= nums.length <= 10000

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 暴力，不太行
class Solution {
    public int[] singleNumbers(int[] nums) {
        Arrays.sort(nums);
        int[] result = new int[2];
        int index = 0;
        for(int i = 1; i < nums.length; i += 2){
            if(nums[i] == nums[i - 1]) continue;
            else{
                result[index++] = nums[i - 1];
                i = i - 1;
                if(index >= 2) break;
            }
        }
        if(index < 2){
            result[index] = nums[nums.length - 1];
        }
        return result;
    }
}

// 位运算
// 思路：
// 对所有数异或，求出两个不同数（x,y)异或的结果
// 然后求出x、y异或结果中，哪一位是1
// 用这一位对原始数据分成两组，两组内部异或，得到两个数，就是结果

// 步骤：
// 1. 求异或结果
// 2. 求异或结果中哪一位为1，该数构成的二进制数记为m
// 3. 根据m分组求异或
class Solution {
    public int[] singleNumbers(int[] nums) {
        int z = 0;
        for(int i : nums){
            z ^= i;
        }
        int m = 1;
        while((z & m) == 0) m <<= 1;
        int x = 0, y = 0;
        for(int i : nums){
            if((i & m) == 0){
                x ^= i;
            }else{
                y ^= i;
            }
        }
        int[] result = new int[]{x, y};
        return result;
    }
}



// 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

//  

// 示例 1:

// 输入: [10,2]
// 输出: "102"
// 示例 2:

// 输入: [3,30,34,5,9]
// 输出: "3033459"

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            strs[i] = String.valueOf(nums[i]);
        }
        quickSort(strs, 0, strs.length - 1);
        String s = "";
        for(int i = 0; i < strs.length; i++){
            s = s + strs[i];
        }
        return s;
    }

    private void quickSort(String[] strs, int l, int r){
        if(l >= r) return;
        int i = l, j = r;
        // 当前处理的元素值是strs[l]
        String curr = strs[l];
        while(i < j) {
            // 从右到左找到第一个比最左元素小的值
            while((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            // 从左到右找到第一个比最左元素大的值
            while((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            // 交换两者
            String tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
            // 循环直到i，j相遇，左边都比这个位置要小，右边都比这个位置要大
        }
        // 将curr放到正确位置，这个位置原来的数替换出来放在l位置准备做下一次处理
        String tmp = strs[i];
        strs[i] = curr;
        strs[l] = tmp;
        // 对两侧排序
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }
}
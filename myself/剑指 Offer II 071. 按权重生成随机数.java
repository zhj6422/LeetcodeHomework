// 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。

// 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。

// 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。

//  

// 示例 1：

// 输入：
// inputs = ["Solution","pickIndex"]
// inputs = [[[1]],[]]
// 输出：
// [null,0]
// 解释：
// Solution solution = new Solution([1]);
// solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
// 示例 2：

// 输入：
// inputs = ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
// inputs = [[[1,3]],[],[],[],[],[]]
// 输出：
// [null,1,1,1,1,0]
// 解释：
// Solution solution = new Solution([1, 3]);
// solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
// solution.pickIndex(); // 返回 1
// solution.pickIndex(); // 返回 1
// solution.pickIndex(); // 返回 1
// solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。

// 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
// [null,1,1,1,1,0]
// [null,1,1,1,1,1]
// [null,1,1,1,0,0]
// [null,1,1,1,0,1]
// [null,1,0,1,0,0]
// ......
// 诸若此类。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/cuyjEf
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    private final Random random = new Random();
    private int[] pre_sum;
    private int sum;
    public Solution(int[] w) {
        pre_sum = new int[w.length];
        for(int i = 0; i < w.length; i++){
            if(i > 0) pre_sum[i] = pre_sum[i - 1] + w[i];
            else pre_sum[i] = w[i];
        }
        sum = pre_sum[pre_sum.length - 1];
    }
    
    public int pickIndex() {
        // 用random生成随机数，看随机数在前缀和数组的哪个位置，返回那个位置
        int target = random.nextInt(sum) + 1;
        int left = 0, right = pre_sum.length - 1;
        // 用二分法求这个位置
        while (left < right) {
            int mid = (left + right) >> 1;
            if (pre_sum[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
// 给定一个三角形 triangle ，找出自顶向下的最小路径和。

// 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

//  

// 示例 1：

// 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
// 输出：11
// 解释：如下面简图所示：
//    2
//   3 4
//  6 5 7
// 4 1 8 3
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
// 示例 2：

// 输入：triangle = [[-10]]
// 输出：-10
//  

// 提示：

// 1 <= triangle.length <= 200
// triangle[0].length == 1
// triangle[i].length == triangle[i - 1].length + 1
// -104 <= triangle[i][j] <= 104

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/IlPe0q
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 三角形深度和宽度都为n
        int n = triangle.size();
        // 构建一个n*n的dp，用上三角来代表到达该位置的最小和
        int[][] dp = new int[n][n];
        // 需要将所有下三角初始化为最大值
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        // 初始化第一个位置及第一行
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++){
            dp[0][i] = dp[0][i-1] + triangle.get(i).get(0);
        }
        res = Math.min(dp[0][n-1], res);

        // 动规完成dp构建
        for(int i = 1; i < n; i++){
            for(int j = i; j < n; j++){
                dp[i][j] = Math.min(dp[i-1][j-1], dp[i][j-1]) + triangle.get(j).get(i);
            }
            // 每完成一行判断当前结果是否符合最小，更新结果
            res = Math.min(res, dp[i][n-1]);
        }
        return res;
    }
}
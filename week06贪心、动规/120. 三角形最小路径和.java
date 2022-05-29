// 给定一个三角形 triangle ，找出自顶向下的最小路径和。

// 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/triangle
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        // 多加一层，最后一层为0，dp[i][j]表示从（i，j）出发的最小路径和
        int[][] dp = new int[height + 1][height + 1];
        // 自底向上求最小路径
        for(int i = height - 1; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
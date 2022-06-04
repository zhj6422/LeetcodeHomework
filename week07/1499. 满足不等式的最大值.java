// 给你一个数组 points 和一个整数 k 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。也就是说 points[i] = [xi, yi] ，并且在 1 <= i < j <= points.length 的前提下， xi < xj 总成立。

// 请你找出 yi + yj + |xi - xj| 的 最大值，其中 |xi - xj| <= k 且 1 <= i < j <= points.length。

// 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/max-value-of-equation
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<Integer> q = new LinkedList<>();
        int result = -(int)1e9;
        for(int i = 0; i < points.length; i++){
            // 如果不在范围内，就删除掉
            while(!q.isEmpty() && points[q.peek()][0] < points[i][0] - k) q.poll();
            // 取最大的结果y[i] + y[j] + x[i] - x[j]
            if(!q.isEmpty()) result = Math.max(result, points[i][1] + points[q.peek()][1] + points[i][0] - points[q.peek()][0]);
            // 维护队列的单调性，将小于当前的内容全部删除，保证y[j] - x[j]单调
            while(!q.isEmpty() && points[q.peekLast()][1] - points[q.peekLast()][0] <= points[i][1] - points[i][0]) q.pollLast();
            q.addLast(i);
        }
        return result;
    }
}
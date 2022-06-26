// 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。

// 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：

// 路径途经的所有单元格都的值都是 0 。
// 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
// 畅通路径的长度 是该路径途经的单元格总数。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/shortest-path-in-binary-matrix
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int n;
    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        if(grid[0][0] != 0 || grid[n-1][n-1] != 0) return -1;
        if(n == 1) return 1;
        Queue<List<Integer>> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        int[] dx = {0, 1, -1, 0, 1, -1, -1, 1};
        int[] dy = {1, 0, 0, -1, 1, 1, -1, -1};
        ArrayList<Integer> first = new ArrayList<>();
        first.add(0);
        first.add(0);
        queue.add(first);
        visited[0][0] = true;
        int[][] dp = new int[n][n];
        dp[0][0] = 1;
        while(!queue.isEmpty()){
            List<Integer> node = queue.poll();
            int x = node.get(0);
            int y = node.get(1);
            for(int i = 0; i < 8; i++){
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if(isValid(nextX, nextY) && grid[nextX][nextY] == 0){
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(nextX);
                    temp.add(nextY);
                    if(!visited[nextX][nextY]){
                        queue.add(temp);
                        visited[nextX][nextY] = true;
                        dp[nextX][nextY] = dp[x][y] + 1;
                    }else{
                        dp[nextX][nextY] = Math.min(dp[nextX][nextY], dp[x][y] + 1);
                    }
                }
            }
        }
        if(dp[n-1][n-1] != 0) return dp[n-1][n-1];
        else return -1;
    }

    private boolean isValid(int x, int y){
        return x < n && x >= 0 && y < n && y >= 0;
    }
}
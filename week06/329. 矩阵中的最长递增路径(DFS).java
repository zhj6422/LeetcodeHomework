class Solution {
    int m;
    int n;
    int[] dx;
    int[] dy;
    int[][] dist; // 用来记录当前点的最大长度
    int[][] matrix;
    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length;
        n = matrix[0].length;
        dx = new int[]{-1, 0, 0, 1};
        dy = new int[]{0, -1, 1, 0};
        dist = new int[m][n];
        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }
    private int dfs(int i, int j){
        // 如果已经查过了，直接返回
        if(dist[i][j] != 0) return dist[i][j];
        // 第一次查，先给赋值1
        dist[i][j] = 1;
        for(int k = 0; k < 4; k++){
            int ni = i + dx[k];
            int nj = j + dy[k];
            if(isValid(ni, nj) && matrix[ni][nj] > matrix[i][j]){
                dist[i][j] = Math.max(dfs(ni, nj) + 1, dist[i][j]);
            }
        }
        return dist[i][j];
    }
    private boolean isValid(int i, int j){
        return i >= 0 && i < m && j >= 0 && j < n;
    }
}
// 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。

// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/longest-increasing-path-in-a-matrix
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int m;
    int n;
    List<List<Integer>> to;
    int[] deg;
    int[] dist;
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        deg = new int[m * n];
        dist = new int[m * n];
        to = new ArrayList<>();
        for(int i = 0; i < m * n; i++){
            to.add(new ArrayList<>());
        }
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < 4; k++){
                    int nextI = i + dx[k];
                    int nextJ = j + dy[k];
                    if(isValid(nextI, nextJ) && matrix[nextI][nextJ] > matrix[i][j]){
                        addEdge(numOfPosition(i, j), numOfPosition(nextI, nextJ));
                    }
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < m * n; i++){
            if(deg[i] == 0){
                q.add(i);
                dist[i] = 1;
            }
        }
        while(!q.isEmpty()){
            int x = q.poll();
            for(int y : to.get(x)){
                deg[y]--;
                dist[y] = Math.max(dist[y], dist[x] + 1);
                if(deg[y] == 0) q.add(y);
            }
        }
        int ans = 0;
        for(int i = 0; i < m * n; i++) ans = Math.max(ans, dist[i]);
        return ans;
    }
    private int numOfPosition(int i, int j){
        return i * n + j;
    }

    private boolean isValid(int i, int j){
        return i >= 0 && i < m && j >= 0 && j < n;
    }
    private void addEdge(int u, int v){
        deg[v]++;
        to.get(u).add(v);
    }
}
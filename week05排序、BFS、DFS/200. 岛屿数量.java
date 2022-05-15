// 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

// 此外，你可以假设该网格的四条边均被水包围。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/number-of-islands
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int m;
    int n;
    boolean[][] visited;
    char[][] grid;
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        visited = new boolean[m][n];
        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j] && grid[i][j] == '1'){
                    ans++;
                    bfs(i, j);
                }
            }
        }
        return ans;
    }
    private void bfs(int x, int y){
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        while(!q.isEmpty()){
            int[] queueNode = q.poll();
            int currX = queueNode[0];
            int currY = queueNode[1];
            visited[currX][currY] = true;
            for(int i = 0; i < 4; i++){
                int nextX = currX + dx[i];
                int nextY = currY + dy[i];
                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
                if(visited[nextX][nextY]) continue;
                if(grid[nextX][nextY] == '0') continue;
                visited[nextX][nextY] = true;
                q.add(new int[]{nextX, nextY});
            }
        }
        
    }
}
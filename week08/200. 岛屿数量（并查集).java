// 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

// 此外，你可以假设该网格的四条边均被水包围。



// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/number-of-islands
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    int[] fa;
    int[] dx = {0, -1};
    int[] dy = {-1, 0};
    int m;
    int n;
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        fa = new int[m * n + 1];
        for(int i = 0; i <= m * n; i++){
            fa[i] = i;
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    for(int k = 0; k < 2; k++){
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];
                        if(isValid(nextX, nextY) && grid[nextX][nextY] == '1'){
                            unionSet(i * n + j, nextX * n + nextY);
                        }
                    }
                }
            }
        }
        HashSet<Integer> resultSet = new HashSet<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    resultSet.add(find(i * n + j));
                }
            }
        }
        return resultSet.size();
    }
    private boolean isValid(int x, int y){
        return x >= 0 && y >= 0 && x < m && y < n;
    }
    private int find(int i){
        if(fa[i] == i) return i;
        return fa[i] = find(fa[i]);
    }

    private void unionSet(int i, int j){
        int x = find(i);
        int y = find(j);
        if(x != y) fa[x] = y;
    }
}
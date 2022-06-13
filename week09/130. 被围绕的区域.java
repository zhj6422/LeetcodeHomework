// 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
//  



// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/surrounded-regions
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int m;
    int n;
    int[] fa;
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        int outside = m * n;
        fa = new int[m * n + 1];
        for(int i = 0; i <= m * n; i++) fa[i] = i;
        // 方向数组，只需要两个方向，向右、向下
        int[] dx = {1, 0};
        int[] dy = {0, 1};
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'X') continue; // 跳过X
                // 对O进行如下遍历
                int curr = getNum(i, j);
                for(int k = 0; k < 2; k++){
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    // 超过范围或在边缘上，证明该O点在边缘，让它和outside并入同一个集合
                    if(ni <= 0 || nj <= 0 || ni >= m || nj >= n){
                        unionSet(curr, outside);
                    }else{
                        // 如果下一步在范围内，让它和相邻的O点并入同一个集合
                        if(board[ni][nj] == 'O'){
                            unionSet(curr, getNum(ni, nj));
                        }
                    }
                }
            }
        }
        outside = find(outside);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O' && find(getNum(i, j)) != outside) board[i][j] = 'X';
            }
        }
    }
    private int getNum(int i, int j){
        return i * n + j;
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
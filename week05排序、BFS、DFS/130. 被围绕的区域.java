// 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

class Solution {
    int m;
    int n;
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 如果O在边缘，就开始标记
                if(isOutside(i, j) && board[i][j] == 'O'){
                    dfs(board, i, j);
                }
            }
        }
        // 将未标记的O改为X，标记的O改回O
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if(board[i][j] == 'C'){
                    board[i][j] = 'O';
                }
            }
        }   
    }
    private boolean isOutside(int i, int j){
        if(i == 0 || j == 0 || i == m - 1 || j == n - 1) return true;
        return false;
    }
    // 对连接边缘的O进行修改，改成C
    private void dfs(char[][] board, int i, int j){
        if(i < 0 || j < 0 || i >= m || j >= n || board[i][j] == 'C' || board[i][j] == 'X') return;
        board[i][j] = 'C';
        dfs(board, i + 1, j);
        dfs(board, i, j + 1);
        dfs(board, i - 1, j);
        dfs(board, i, j - 1);
    }
}
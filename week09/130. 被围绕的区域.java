// 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
//  



// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/surrounded-regions
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int[] fa;
    int m;
    int n;
    public void solve(char[][] board) {
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};
        m = board.length;
        n = board[0].length;
        fa = new int[m * n + 1];
        int outside = m * n;
        for(int i = 0; i <= m * n; i++) fa[i] = i;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'X') continue;
                for(int k = 0; k < 4; k++){
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    if(ni < 0 || nj < 0 || ni >= m || nj >= n){
                        unionSet(num(i, j), outside);
                    }else{
                        if(board[ni][nj] == 'O'){
                            unionSet(num(i, j), num(ni, nj));
                        }
                    }
                }
            }
        }
        outside = find(outside);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O' && find(num(i, j)) != outside){
                    board[i][j] = 'X';
                }
            }
        }
    }
    private int num(int i, int j){
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
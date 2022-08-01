// 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int k;
    int count = 1;
    boolean[][] isVisited;
    int m;
    int n;
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};
    public int movingCount(int m, int n, int k) {
        this.k = k;
        this.m = m;
        this.n = n;
        isVisited = new boolean[m][n];
        isVisited[0][0] = true;
        move(0, 0);
        return count;
    }
    private void move(int i, int j){
        if(i < 0 || i >= m || j < 0 || j >= n) return;
        isVisited[i][j] = true;
        for(int k = 0; k < 4; k++){
            int ni = i + dx[k];
            int nj = j + dy[k];
            if(ni < 0 || ni >= m || nj < 0 || nj >= n) continue;
            if(isVisited[ni][nj]) continue;
            if(isValidForIndex(ni, nj)){
                count++;
                move(ni, nj);
            }
        }
    }
    private boolean isValidForIndex(int i, int j){
        int sum = 0;
        while(i > 0){
            sum += i % 10;
            i /= 10;
        }
        while(j > 0){
            sum += j % 10;
            j /= 10;
        }
        return sum <= k;
    }
}
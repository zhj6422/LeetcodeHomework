// 编写一个程序，通过填充空格来解决数独问题。

// 数独的解法需 遵循如下规则：

// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
// 数独部分空格内已填入了数字，空白格用 '.' 表示。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/sudoku-solver
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 先找所有能填数最少的空格先填，减少遍历的次数，减少耗时（leetcode用时2ms）

class Solution {
    char[][] board;
    boolean[][] row = new boolean[9][10];
    boolean[][] col = new boolean[9][10];
    boolean[][] box = new boolean[9][10];
    public void solveSudoku(char[][] board) {
        this.board = board;
        for(int i = 0; i < 9; i++){
            for(int digit = 1; digit <= 9; digit++){
                row[i][digit] = col[i][digit] = box[i][digit] = true;
            }
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                int digit = board[i][j] - '0';
                row[i][digit] = false;
                col[j][digit] = false;
                box[boxIndex(i, j)][digit] = false;
            }
        }
        dfs();
    }
    private boolean dfs(){
        // 找最确定，可能性的情况最少的格子
        int[] pos = findMinimumProbability();
        int x = pos[0];
        int y = pos[1];
        if(x == -1) return true;
        // 拿到x，y这个空位可用的数字
        List<Integer> avalibaleDigits = getAvailableDigits(x, y);
        for(Integer digit : avalibaleDigits){
            board[x][y] = (char)('0' + digit);
            row[x][digit] = false;
            col[y][digit] = false;
            box[boxIndex(x, y)][digit] = false;
            if(dfs()) return true;
            row[x][digit] = true;
            col[y][digit] = true;
            box[boxIndex(x, y)][digit] = true;
            board[x][y] = '.';
        }
        return false;
    }
    private int[] findMinimumProbability(){
        int minValue = 10;
        int[] pos = new int[]{-1, -1};
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.') continue;
                List<Integer> avalibaleDigits = getAvailableDigits(i, j);
                if(avalibaleDigits.size() < minValue){
                    minValue = avalibaleDigits.size();
                    pos[0] = i; 
                    pos[1] = j;
                }
            }
        }
        return pos;
    }
    private List<Integer> getAvailableDigits(int i, int j){
        List<Integer> avalibaleDigits = new ArrayList<>();
        for(int digit = 1; digit <= 9; digit++){
            if(row[i][digit] && col[j][digit] && box[boxIndex(i, j)][digit]){
                avalibaleDigits.add(digit);
            }
        }
        return avalibaleDigits;
    }

    private int boxIndex(int i, int j){
        return (i / 3) * 3 + (j / 3);
    }
    
}
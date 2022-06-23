// 编写一个程序，通过填充空格来解决数独问题。

// 数独的解法需 遵循如下规则：

// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
// 数独部分空格内已填入了数字，空白格用 '.' 表示。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/sudoku-solver
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 这个方法太耗时（leetcode用时1209 ms），因为每次选一个空格，填的时候遍历9种情况，而不关心空格的顺序

class Solution {
    char[][] board;
    public void solveSudoku(char[][] board) {
        this.board = board;
        dfs();
    }
    private boolean dfs(){
        // 查看当前是否符合要求
        if(!isValid(board)) return false;
        // 找第一个空位置
        int[] pos = findFirstEmpty();
        int x = pos[0];
        int y = pos[1];
        if(x == -1) return true;
        for(char digit = '1'; digit <= '9'; digit++){
            board[x][y] = digit;
            if(dfs()) return true;
            board[x][y] = '.';
        }
        return false;
    }
    private int[] findFirstEmpty(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char digit = board[i][j];
                if(digit == '.') return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }
    private boolean isValid(char[][] board) {
        ArrayList<HashSet<Character>> row = new ArrayList<>();
        ArrayList<HashSet<Character>> col = new ArrayList<>();
        ArrayList<HashSet<Character>> box = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            row.add(new HashSet<>());
            col.add(new HashSet<>());
            box.add(new HashSet<>());
        }
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                char digit = board[i][j];
                if (digit == '.') continue;
                if (row.get(i).contains(digit)) return false;
                row.get(i).add(digit);
                if (col.get(j).contains(digit)) return false;
                col.get(j).add(digit);
                int k = (i / 3) * 3 + j / 3;
                if (box.get(k).contains(digit)) return false;
                box.get(k).add(digit);
            }
        return true;

    }
}
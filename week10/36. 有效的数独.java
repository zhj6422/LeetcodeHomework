// 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
//  

// 注意：

// 一个有效的数独（部分已被填充）不一定是可解的。
// 只需要根据以上规则，验证已经填入的数字是否有效即可。
// 空白格用 '.' 表示。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/valid-sudoku
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    public boolean isValidSudoku(char[][] board) {
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
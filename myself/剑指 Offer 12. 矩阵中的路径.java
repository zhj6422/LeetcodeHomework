// 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int n;
    int m;
    char[] wordChar;
    char[][] board;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        n = board.length;
        m = board[0].length;
        wordChar = word.toCharArray();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!findWord(i, j, 0)) continue;
                else return true;
            }
        }
        return false;
    }
    private boolean findWord(int i, int j, int wordIndex){
        boolean result = false;
        if(wordIndex == wordChar.length) return true;
        if(!isValid(i, j)) return false;
        if(board[i][j] == wordChar[wordIndex]){
            board[i][j] = '0';
            result = findWord(i + 1, j, wordIndex + 1) || findWord(i - 1, j, wordIndex + 1) || findWord(i, j + 1, wordIndex + 1) || findWord(i, j - 1, wordIndex + 1);
            board[i][j] = wordChar[wordIndex];
        }
        return result;
    }
    private boolean isValid(int i, int j){
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
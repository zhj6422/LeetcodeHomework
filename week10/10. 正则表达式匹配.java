// 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/regular-expression-matching
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        // 添加空格，遍历从1开始
        s = " " + s;
        p = " " + p;
        // f数组，f[i][j]表示s的前i部分和p的前j部分是否能匹配
        boolean[][] f = new boolean[n+1][m+1];
        // 空格部分预处理
        f[0][0] = true;
        for(int j = 2; j <= m; j += 2){
            // * 可以匹配0个到多个
            if(p.charAt(j) == '*') f[0][j] = true;
            else break;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(p.charAt(j) >= 'a' && p.charAt(j) <= 'z') {
                    // 如果是字母，s和p要匹配得上，并且前面完成匹配
                    f[i][j] = f[i-1][j-1] && s.charAt(i) == p.charAt(j);
                }else if(p.charAt(j) == '.'){
                    // 如果是点，前面匹配上即可，这个位置可以是任意字符
                    f[i][j] = f[i-1][j-1];
                }else{
                    // 剩下的情况，如果当前是*
                    // * 不继续配了
                    f[i][j] = f[i][j-2];
                    // * 继续配
                    if(s.charAt(i)==p.charAt(j-1) || p.charAt(j-1) == '.') f[i][j] = f[i][j] || f[i-1][j];
                }
            }
        }
        return f[n][m];
    }
}
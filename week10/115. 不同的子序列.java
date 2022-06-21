// 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。

// 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

// 题目数据保证答案符合 32 位带符号整数范围。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/distinct-subsequences
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        s = " " + s;
        t = " " + t;
        // f[i][j] 表示s[1~i]选自序列得到t[1~j]的方案数
        // f[i][j] = f[i-1][j]
        // 如果s[i]==t[j] 有f[i][j] += f[i-1][j-1];
        // 初值：f[i][0] = 1;
        // 目标：f[n][m]

        int[][] f = new int[n+1][m+1];
        for(int i = 0; i <= n; i++){
            f[i][0] = 1;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                f[i][j] = f[i - 1][j];
                if(s.charAt(i) == t.charAt(j) && f[i][j] <= 2147483547 - f[i - 1][j - 1]) f[i][j] += f[i - 1][j - 1];
            }
        }
        return f[n][m];
    }
}
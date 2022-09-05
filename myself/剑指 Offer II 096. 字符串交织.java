// 给定三个字符串 s1、s2、s3，请判断 s3 能不能由 s1 和 s2 交织（交错） 组成。

// 两个字符串 s 和 t 交织 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

// s = s1 + s2 + ... + sn
// t = t1 + t2 + ... + tm
// |n - m| <= 1
// 交织 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
// 提示：a + b 意味着字符串 a 和 b 连接。

//  

// 示例 1：



// 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
// 输出：true
// 示例 2：

// 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
// 输出：false
// 示例 3：

// 输入：s1 = "", s2 = "", s3 = ""
// 输出：true
//  

// 提示：

// 0 <= s1.length, s2.length <= 100
// 0 <= s3.length <= 200
// s1、s2、和 s3 都由小写英文字母组成

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/IY6buf
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        int n = c1.length;
        int m = c2.length;
        int o = c3.length;
        if(o != n + m) return false;
        // dp[i][j] 表示s1的编号i前部分（长度i+1）和s2的编号j前部分（长度j+1）能否构成s3的编号i+j+1前部分（长度i+j+2）
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for(int i = 0; i < n; i++){
            if(c1[i] == c3[i]) dp[i+1][0] = true;
            else break;
        }
        for(int i = 0; i < m; i++){
            if(c2[i] == c3[i]) dp[0][i+1] = true;
            else break;
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                char ch1 = c1[i];
                char ch2 = c2[j];
                char ch3 = c3[i+j+1];
                dp[i+1][j+1] = ((ch1 == ch3) && dp[i][j + 1]) || ((ch2 == ch3) && dp[i + 1][j]);
            }
        }
        return dp[n][m];
        
    }
}
// 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。

// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

//  

// 示例 1：

// 输入：s = "abc"
// 输出：3
// 解释：三个回文子串: "a", "b", "c"
// 示例 2：

// 输入：s = "aaa"
// 输出：6
// 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/a7VOhD
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


// 动规
// 二维dp数组，dp[i][j]表示区间（i，j）是否是回文子串
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int i = 0; i < n; i++){
            dp[i][i] = true;
        }
        int res = n;
        // 主要是遍历顺序，左节点从后往前，右节点从左节点开始往后，保证每一项都能遍历到，而且后来计算的可以用到前面计算结果
        for(int i = n - 1; i > -1; i--){
            for(int j = i + 1; j < n; j++){
                if(s.charAt(j) == s.charAt(i)){
                    if(j == i + 1){
                        dp[i][j] = true;
                        res++;
                    }else if(dp[i + 1][j - 1]){
                        dp[i][j] = true;
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
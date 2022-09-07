// 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

//  

// 示例 1：

// 输入：text1 = "abcde", text2 = "ace" 
// 输出：3  
// 解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 示例 2：

// 输入：text1 = "abc", text2 = "abc"
// 输出：3
// 解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 示例 3：

// 输入：text1 = "abc", text2 = "def"
// 输出：0
// 解释：两个字符串没有公共子序列，返回 0 。
//  

// 提示：

// 1 <= text1.length, text2.length <= 1000
// text1 和 text2 仅由小写英文字符组成。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/qJnOS7
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。




// 尝试得到两个String的所有子序列，然后保存到set中，根据两个set中是否存在相同内容，来判断公共子序列，然后更新最大长度
// 但是会超时，如果String过长
class Solution {
    
    public int longestCommonSubsequence(String text1, String text2) {
        int res = 0;
        String path1 = "";
        String path2 = "";
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        back(chars1, 0, set1, path1);
        back(chars2, 0, set2, path2);
        for(String str : set1){
            int len = str.length();
            if(set2.contains(str)){
                res = Math.max(res, len);
            }
        }
        return res;
    }

    private void back(char[] chars, int startIndex, HashSet<String> tempSet, String path){
        if(startIndex >= chars.length) return;
        for(int i = startIndex; i < chars.length; i++){
            path = path + chars[i];
            tempSet.add(path);
            back(chars, i + 1, tempSet, path);
            path = path.substring(0, path.length() - 1);
        }
    }
}

// ============================分割线==============================

// dp解决超时问题
// 用二维dp数组保存字符串公共子序列的最长长度
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        // dp[i][j] 表示text1的前i和text2的前j的最大公共子序列长度
        int[][] dp = new int[n][m];
        // 初始化，第一行、第一列，遇到相同的之后，后面全是1
        for(int i = 0; i < n; i++){
            if(text2.charAt(0) == text1.charAt(i) || (i >= 1 && dp[i-1][0] == 1)) dp[i][0] = 1;
        }
        for(int i = 0; i < m; i++){
            if(text1.charAt(0) == text2.charAt(i) || (i >= 1 && dp[0][i-1] == 1)) dp[0][i] = 1;
        }
        // 如果当前位置相同，则在dp[i-1][j-1]基础上加一
        // 如果当前位置不同，则在dp[i][j-1],dp[i-1][j]中取最大的
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n-1][m-1];
    }
}
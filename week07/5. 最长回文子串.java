// 给你一个字符串 s，找到 s 中最长的回文子串。

class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len < 2) return s;
        // dp[i][j]表示从i到j的区间是不是回文子串（包括i和j）
        boolean[][] dp = new boolean[len][len];
        for(int i = 0; i < len; i++){
            // 单独一个字母为回文子串
            dp[i][i] = true;
        }
        char[] strArray = s.toCharArray();
        // 最长子串的开始坐标和长度
        int maxIndex = 0;
        int maxLen = 1;
        for(int j = 1; j < len; j++){
            for(int i = 0; i < j; i++){
                if(strArray[i] != strArray[j]){
                    dp[i][j] = false;
                }else{
                    if(j - i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                // 当确定是回文子串的时候，如果比之前记录的最长子串要长，就更新
                if(dp[i][j] && maxLen < j - i + 1){
                    maxLen = j - i + 1;
                    maxIndex = i;
                }
            }
        }
        // 根据最长子串开始坐标和长度获取结果
        String result = s.substring(maxIndex, maxIndex + maxLen);
        return result;
    }
}
// 给你一个字符串 s，找到 s 中最长的回文子串。

class Solution {
    public String longestPalindrome(String s) {
        // 以字母为中心向两侧扩展
        int n = s.length();
        int resultLen = 0;
        int resultStart = 0;
        for(int center = 0; center < n; center++){
            int l = center - 1;
            int r = center + 1;
            while(l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
                l--;
                r++;
            }
            if(r - l - 1 > resultLen){
                resultLen = r - l - 1;
                resultStart = l + 1;
            }
        }

        // 以字母之间的空为中心
        for(int center = 1; center < n; center++){
            int l = center - 1;
            int r = center;
            while(l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
                l--;
                r++;
            }
            if(r - l - 1 > resultLen){
                resultLen = r - l - 1;
                resultStart = l + 1;
            }
        }
        return s.substring(resultStart, resultStart + resultLen);
    }
}
// 编写一个函数来查找字符串数组中的最长公共前缀。

// 如果不存在公共前缀，返回空字符串 ""。


class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if(n == 0) return "";
        String result = strs[0];
        int index = 1;
        while(index < n){
            String next = strs[index];
            if(next.length() < result.length()) result = result.substring(0, next.length());
            if(next.equals("") || result.equals("")) return "";
            for(int i = 0; i < result.length(); i++){
                if(i < next.length() && result.charAt(i) != next.charAt(i)){
                    result = result.substring(0, i);
                    break;
                }
            }
            index++;
        }
        return result;
    }
}
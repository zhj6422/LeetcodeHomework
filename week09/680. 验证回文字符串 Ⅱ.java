// 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。


class Solution {
    public boolean validPalindrome(String s) {
        return check(s, 0, s.length() - 1, 1);
    }

    // 多了一个参数time表示可以容错多少次
    private boolean check(String s, int l, int r, int time){
        while(l < r){
            if(s.charAt(l) != s.charAt(r)){
                return time > 0 && (check(s, l + 1, r, 0) || check(s, l, r - 1, 0));
            }
            l++;
            r--;
        }
        return true;
    }
}
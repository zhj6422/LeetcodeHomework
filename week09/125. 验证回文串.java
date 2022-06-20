// 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

// 说明：本题中，我们将空字符串定义为有效的回文串。

// 不使用额外空间存储String，直接扫描s，通过判断是否合法来决定下一个要访问的字符
class Solution {
    public boolean isPalindrome(String s) {
        int l = getNext(s, 0), r = getPre(s, s.length() - 1);
        while(l < r){
            if(!isEqual(s.charAt(l), s.charAt(r))) return false;
            l = getNext(s, l + 1);
            r = getPre(s, r - 1);
        }
        return true;
    }

    private boolean isValid(char c){
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'); 
    }

    private int getNext(String s, int i){
        while(i < s.length() && !isValid(s.charAt(i))) i++;
        return i;
    }

    private int getPre(String s, int i){
        while(i >= 0 && !isValid(s.charAt(i))) i--;
        return i;
    }

    private boolean isEqual(char a, char b){
        if(a >= 'A' && a <= 'Z') a = (char)(a - 'A' + 'a');
        if(b >= 'A' && b <= 'Z') b = (char)(b - 'A' + 'a');
        return a == b;
    }
}
// 给你一个字符串 s ，根据下述规则反转字符串：

// 所有非英文字母保留在原有位置。
// 所有英文字母（小写或大写）位置反转。
// 返回反转后的 s 。

// 双指针从两侧向中间扫描，遇到非字母的跳过
class Solution {
    char[] chars;
    public String reverseOnlyLetters(String s) {
        chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            while(i < j && !isLetter(s.charAt(i))) i++;
            while(i < j && !isLetter(s.charAt(j))) j--;
            swap(i, j);
            i++;
            j--;
        }
        return new String(chars);
    }
    private void swap(int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private boolean isLetter(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
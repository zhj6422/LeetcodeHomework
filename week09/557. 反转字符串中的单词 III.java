// 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

// 输入：s = "Let's take LeetCode contest"
// 输出："s'teL ekat edoCteeL tsetnoc"

class Solution {
    char[] chars;
    public String reverseWords(String s) {
        chars = s.toCharArray();
        int left = 0;
        int right = 0;
        while(right < s.length()){
            // 找这个单词的结束位置
            while(right < s.length() && s.charAt(right) != ' ') right++;
            reverseChar(left, right - 1);
            // 找下一个单词的开始位置
            while(right < s.length() && s.charAt(right) == ' ') right++;
            left = right;
        }
        return new String(chars);
    }
    private void reverseChar(int start, int end){
        while(start < end){
            swap(start, end);
            start++;
            end--;
        }
    }
    private void swap(int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
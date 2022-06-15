// 给你一个字符串 s ，颠倒字符串中 单词 的顺序。

// 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。

// 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。

// 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/reverse-words-in-a-string
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 先将整个字符串倒序，然后根据单词再将每个单词倒序
class Solution {
    char[] chars;
    public String reverseWords(String s) {
        s = tripPlane(s);
        chars = s.toCharArray();
        int len = chars.length;
        reversChars(0, len - 1);
        int left = 0, right = 0;
        while(right < len){
            while(right < len && chars[right] != ' ') right++;
            reversChars(left, right - 1);
            while(right < len && chars[right] == ' ') right++;
            left = right;
        }
        return new String(chars);
    }


    private String tripPlane(String s){
        int i = 0;
        int j = 0;
        String result = "";
        while(j < s.length()){
            // 找到每个单词的开头
            while(j < s.length() && s.charAt(j) == ' '){
                j++;
            }
            i = j;
            // 找到每个单词的结尾
            while(j < s.length() && s.charAt(j) != ' ') j++;
            // 加入返回字符串中
            if(i != j) result += s.substring(i, j) + " ";
        }
        // 移除最后一个空格
        return result.substring(0, result.length() - 1);
    }
    private void reversChars(int start, int end){
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

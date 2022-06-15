// 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。

class Solution {
    public String toLowerCase(String s) {
        char[] charArray = s.toCharArray();
        for(int i = 0; i < charArray.length; i++){
            if(charArray[i] >= 'A' && charArray[i] <= 'Z'){
                int len = charArray[i] - 'A';
                int start = (int)'a';
                char temp = (char)(start + len);
                charArray[i] = temp;
            }
        }
        String result = new String(charArray);
        return result;
    }
}
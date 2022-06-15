// 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。

class Solution {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        int[] startIndex = new int[26];
        for(int i = 0; i < startIndex.length; i++){
            startIndex[i] = -1;
        }
        for(int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'a';
            count[index]++;
            if(startIndex[index] == -1) startIndex[index] = i;
        }
        int result = -1;
        for(int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'a';
            if(count[index] == 1) {
                return result = startIndex[index];
            }
        }
        return result;
    }
}
// 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。

// 如果剩余字符少于 k 个，则将剩余字符全部反转。
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/reverse-string-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    char[] chars;
    public String reverseStr(String s, int k) {
        chars = s.toCharArray();
        int len = chars.length;
        int i = 1;
        // 每次处理的区间是 (i - 1) * k 到 i * k - 1
        while(i * k - 1 < len){
            reverseChildStr((i - 1) * k, i * k - 1);
            i += 2; // 举例：第一次从0到k-1，第二次从2k到3k-1，每次倍数i增加2
        }
        // 处理最后一小段，如果存在的话
        if((i - 1) * k < len) reverseChildStr((i - 1) * k, len - 1);
        return new String(chars);
    }

    private void reverseChildStr(int i, int j){
        while(i < j){
            swap(i, j);
            i++;
            j--;
        }
    }
    private void swap(int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
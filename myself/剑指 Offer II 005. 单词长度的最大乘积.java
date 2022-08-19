// 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。

//  

// 示例 1:

// 输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
// 输出: 16 
// 解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
// 示例 2:

// 输入: words = ["a","ab","abc","d","cd","bcd","abcd"]
// 输出: 4 
// 解释: 这两个单词为 "ab", "cd"。
// 示例 3:

// 输入: words = ["a","aa","aaa","aaaa"]
// 输出: 0 
// 解释: 不存在这样的两个单词。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/aseY1I
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


/*
 * 主要解决怎么确定两个字符串之间包不包含相同的字母
 * 用位运算来确定
 * 遍历每个字符串，用一个数来表示这个字符串
 * 这个数怎么来？通过位运算来
 * 先把数t初始化为0，遍历字符串每一位，如果是a，就将t的0位变成1（或运算），如果是b就把t的第1位变成1……
 * 将表示所有字符串的数保存在一个数组masks里面
 * 遍历这个数组，取两个数，两两之间按位与，如果是0，表示这两个数之间没有相同的字母（所有位都最多只有一个为1，你有a我就没有a，我有b你就没b）
 * 如果按位与之后是0，则更新最大值
 * 最后遍历完masks数组，得到结果
 */

class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        int index = 0;
        for(int i = 0; i < n; i++){
            char[] c = words[i].toCharArray();
            int t = 0;
            for(int j = 0; j < c.length; j++){
                t |= 1 << (c[j] - 'a');
            }
            masks[index++] = t;
        }
        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if((masks[i] & masks[j]) == 0){
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }
}
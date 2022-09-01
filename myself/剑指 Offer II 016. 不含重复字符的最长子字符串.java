// 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。

//  

// 示例 1:

// 输入: s = "abcabcbb"
// 输出: 3 
// 解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。
// 示例 2:

// 输入: s = "bbbbb"
// 输出: 1
// 解释: 因为无重复字符的最长子字符串是 "b"，所以其长度为 1。
// 示例 3:

// 输入: s = "pwwkew"
// 输出: 3
// 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 示例 4:

// 输入: s = ""
// 输出: 0

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/wtcaE1
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


// 滑动窗口
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        // 存放结果
        int res = 0;
        // 保存出现过的字符
        HashSet<Character> set = new HashSet<>();
        // 固定左窗口，滑动右窗口
        int left = 0;
        for(int right = 0; right < n; right++){
            char curr = s.charAt(right);
            if(!set.contains(curr)){
                // 不包含当前字符，加入set，并更新结果
                set.add(curr);
                res = Math.max(res, right - left + 1);
            }else{
                // 包含当前字符，则找到之前出现的位置，移动左窗口
                char head = s.charAt(left);
                while(head != curr){
                    set.remove(head);
                    head = s.charAt(++left);
                }
                // 找到之后，不用更新set了（更新相当于把出现过的字符移除，然后又把当前字符加入，其实是相同的字符），这里找到了相同字符的左侧窗口，只需要再向右移动一位即可
                left++;
            }
        }
        return res;
    }
}
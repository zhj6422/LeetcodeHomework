// 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

//  

// 示例 1:

// 输入: "abcabcbb"
// 输出: 3 
// 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 示例 2:

// 输入: "bbbbb"
// 输出: 1
// 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 示例 3:

// 输入: "pwwkew"
// 输出: 3
// 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    HashSet<Character> set = new HashSet<>();
    LinkedList<Character> list = new LinkedList<>();
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int len = 0;
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++){
            if(!set.contains(c[i])){
                set.add(c[i]);
                len++;
                list.add(c[i]);
            }else{
                // 出现重复了，判断当前的长度是否比之前的长，是的话更新
                if(len > result) result = len;
                // 删除重复元素出现前的元素
                while(list.getFirst() != c[i]){
                    char first = list.getFirst();
                    list.removeFirst();
                    set.remove(first);
                    len--;
                }
                // 删除重复元素，并将元素加到list最后
                list.removeFirst();
                list.add(c[i]);
            }
        }
        if(len > result) result = len;
        return result;
    }
}
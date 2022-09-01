// 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

// 变位词 指字母相同，但排列不同的字符串。

//  

// 示例 1：

// 输入: s = "cbaebabacd", p = "abc"
// 输出: [0,6]
// 解释:
// 起始索引等于 0 的子串是 "cba", 它是 "abc" 的变位词。
// 起始索引等于 6 的子串是 "bac", 它是 "abc" 的变位词。
//  示例 2：

// 输入: s = "abab", p = "ab"
// 输出: [0,1,2]
// 解释:
// 起始索引等于 0 的子串是 "ab", 它是 "ab" 的变位词。
// 起始索引等于 1 的子串是 "ba", 它是 "ab" 的变位词。
// 起始索引等于 2 的子串是 "ab", 它是 "ab" 的变位词。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/VabMRr
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 滑动窗口
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length();
        int m = p.length();
        List<Integer> res = new ArrayList<>();
        if(n < m) return res;
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for(int i = 0; i < m; i++){
            arr1[s.charAt(i) - 'a']++;
            arr2[p.charAt(i) - 'a']++;
        }
        for(int i = m; i < n; i++){
            if(Arrays.equals(arr1, arr2)) res.add(i - m);
            arr1[s.charAt(i - m) - 'a']--;
            arr1[s.charAt(i) - 'a']++;
        }
        if(Arrays.equals(arr1, arr2)) res.add(n - m);
        return res;
    }
}
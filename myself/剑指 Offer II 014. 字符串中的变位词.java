// 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。

// 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。

//  

// 示例 1：

// 输入: s1 = "ab" s2 = "eidbaooo"
// 输出: True
// 解释: s2 包含 s1 的排列之一 ("ba").
// 示例 2：

// 输入: s1= "ab" s2 = "eidboaoo"
// 输出: False

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/MPnaiL
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


// 暴力
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if(m < n) return false;
        int[] count = new int[26];
        for(int i = 0; i < n; i++){
            count[s1.charAt(i) - 'a']++;
        }
        boolean valid = false;
        for(int i = 0; i < m - n + 1; i++){
            valid = isValid(count, s2, i, n);
            if(valid) return true;
        }
        return false;
    }

    private boolean isValid(int[] count, String s2, int i, int n){
        int[] temp = (int[]) Arrays.copyOf(count, 26);
        for(int j = i; j < i + n; j++){
            temp[s2.charAt(j) - 'a']--;
            if(temp[s2.charAt(j) - 'a'] < 0) return false;
        }
        return true;
    }
}



// 滑动窗口法
// 活用Arrays.equals判断数组是否相等
// 将字符串转换为长度为26的数组
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if(n > m) return false;
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for(int i = 0; i < n; i++){
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }
        for(int i = n; i < m; i++){
            if(Arrays.equals(arr1, arr2)) return true;
            arr2[s2.charAt(i - n) - 'a']--;
            arr2[s2.charAt(i) - 'a']++;
        }
        return Arrays.equals(arr1, arr2);
    }
}
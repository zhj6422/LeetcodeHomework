// 实现 strStr() 函数。

// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。

// 说明：

// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/implement-strstr
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int strStr(String haystack, String needle) {
        // 取p为一个大质数
        int b = 131, p = (int)1e9 + 7;
        int n = haystack.length();
        int m = needle.length();
        long[] H = new long[n + 1];
        for(int i = 1; i <= n; i++){
            // a = 1, b = 2, …… , z = 26
            H[i] = (H[i - 1] * b + (haystack.charAt(i - 1) - 'a' + 1)) % p;
        }
        long HashNeedle = 0;
        long powBM = 1; // 存储b的m次方
        for(int i = 1; i <= m; i++){
            HashNeedle = (HashNeedle * b + (needle.charAt(i - 1) - 'a' + 1)) % p;
            powBM = (powBM * b) % p;
        }
        for(int l = 1; l <= n - m + 1; l++){
            int r = l + m - 1;
            // 获取子串的Hash，+p再对p取模，防止出现负数的情况
            long HashTemp = ((H[r] - H[l - 1] * powBM) % p + p) % p;
            if(HashTemp == HashNeedle) return l - 1;
        }
        return -1;
    }
}
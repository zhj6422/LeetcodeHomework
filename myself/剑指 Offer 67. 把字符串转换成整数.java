// 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

//  

// 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

// 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

// 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

// 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

// 在任何情况下，若函数不能进行有效的转换时，请返回 0。

// 说明：

// 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。



// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


// 1. 前面空格，trim直接去掉
// 2. 正负号用一个sign保存
// 3. 遇到非数字字符立马停止
// 4. 边界：Integer最大值除以10，如果当前等于这个，且下一个数超过7，表示下一个会超过范围
class Solution {
    public int strToInt(String str) {
        char[] c = str.trim().toCharArray();
        int sign = 1;
        int index = 0;
        if(c.length == 0) return 0;
        if(c[0] == '-') sign = -1;
        if(c[0] == '+' || c[0] == '-') index = 1;
        int bound = 214748364;
        int result = 0;
        for(int i = index; i < c.length; i++){
            if(c[i] < '0' || c[i] > '9') break;
            if(result > bound || (result >= bound && c[i] > '7')){
                if(sign == 1) return 2147483647;
                else return -2147483648;
            }
            result *= 10;
            result += c[i] - '0';
        }
        return result * sign;
    }
}
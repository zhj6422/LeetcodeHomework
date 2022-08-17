// 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。

//  

// 示例:

// 输入: [1,2,3,4,5]
// 输出: [120,60,40,30,24]
//  

// 提示：

// 所有元素乘积之和不会溢出 32 位整数
// a.length <= 100000

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



// 表格分区
//         a[0]  a[1]  a[3]  a[3]  a[4]  
// res[0]   1     x     x     x     x
// res[1]   x     1     x     x     x
// res[2]   x     x     1     x     x
// res[3]   x     x     x     1     x
// res[4]   x     x     x     x     1
// 自定义res[0] = 1，然后乘下三角的数值，之后乘上三角的数值，即可得到res数组


class Solution {
    public int[] constructArr(int[] a) {
        int len = a.length;
        int[] res = new int[len];
        if(len == 0) return res;
        res[0] = 1;
        for(int i = 1; i < len; i++){
            res[i] = res[i - 1] * a[i - 1];
        }
        int temp = 1;
        for(int i = len - 2; i >= 0; i--){
            temp *= a[i + 1];
            res[i] *= temp;
        }
        return res;
    }
}
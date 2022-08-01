// 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

//  

// 参考以下这颗二叉搜索树：

//      5
//     / \
//    2   6
//   / \
//  1   3
// 示例 1：

// 输入: [1,6,3,2,5]
// 输出: false
// 示例 2：

// 输入: [1,3,2,6,5]
// 输出: true

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return isValid(postorder, 0, postorder.length - 1);
    }
    private boolean isValid(int[] postorder, int left, int right){
        // 遍历完了，返回true
        if(left >= right) return true;
        int temp = left;
        // 找左子树
        while(postorder[temp] < postorder[right]) temp++;
        // 记录左右子树分隔开的位置
        int nextIndex = temp;
        // 找右子树
        while(postorder[temp] > postorder[right]) temp++;
        // 如果找完左子树右子树，temp还不等于right，表示存在不满足二叉搜索树部分，返回false，然后对左右子树的区间做相同操作
        return temp == right && isValid(postorder, left, nextIndex - 1) && isValid(postorder, nextIndex, right - 1);
    }
}
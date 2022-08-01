// 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

// B是A的子结构， 即 A中有出现和B相同的结构和节点值。

// 例如:
// 给定的树 A:

//      3
//     / \
//    4   5
//   / \
//  1   2
// 给定的树 B：

//    4 
//   /
//  1
// 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // B是不是A的子结构
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 如果A是空，B不可能是；约定空树不是任意一个树的子结构，所以B为空，也不是
        if(A == null || B == null) return false;
        // 判断B是不是以当前A为起点的子结构，或者以A左节点为起点的子结构，或者以A右节点为起点的子结构
        return isCurrNodeSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }
    // B是不是当前A节点的子结构
    private boolean isCurrNodeSub(TreeNode A, TreeNode B){
        // 如果B已经为空了，表示遍历完B了，返回true
        if(B == null) return true;
        // B还没遍历完，但是A已经空了，表示不能匹配
        if(A == null) return false;
        // 当前值不相同，不能匹配
        if(A.val != B.val) return false;
        // 若不为空，且当前值相同，则看左右子节点能否匹配
        return isCurrNodeSub(A.left, B.left) && isCurrNodeSub(A.right, B.right);
    }
}
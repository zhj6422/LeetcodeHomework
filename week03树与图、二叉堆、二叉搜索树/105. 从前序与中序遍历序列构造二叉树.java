// 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    int[] preorder;
    int[] inorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 保存，不用传递到递归函数中
        this.preorder = preorder;
        this.inorder = inorder;
        TreeNode root = build(0, preorder.length - 1, 0, inorder.length - 1);
        return root;
    }

    private TreeNode build(int l1, int r1, int l2, int r2){
        // 终止条件
        if(l1 > r1) return null;
        TreeNode root = new TreeNode(preorder[l1]);
        // 找到中序遍历中根节点所在的位置
        int mid = l2;
        while(inorder[mid] != root.val) mid++;
        // 在先序遍历的数组中，左子树的范围从l1+1开始，长度是mid-l2，终点位置是l1 + (mid - l2)；
        // 右子树的范围从l1 + (mid - l2) + 1开始，到r1
        root.left = build(l1 + 1, l1 + (mid - l2), l2, mid - 1);
        // 中序遍历数组中，左子树的范围从l2开始，到mid-1；
        // 右子树的范围从mid+1开始到r2
        root.right = build(l1 + (mid - l2) + 1, r1, mid + 1, r2);
        return root;
    }
}
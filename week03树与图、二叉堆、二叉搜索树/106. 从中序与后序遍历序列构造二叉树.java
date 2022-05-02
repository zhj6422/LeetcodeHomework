// 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
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
    // 存起来，不用放到递归函数作为参数
    int[] inorder;
    int[] postorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        return getRoot(0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode getRoot(int l1, int r1, int l2, int r2){
        if(l1 > r1) return null;
        TreeNode root = new TreeNode(postorder[r2]);
        // 从中序中找区分左右两侧的长度
        int len = 0;
        while(inorder[l1 + len] != postorder[r2]) len++;
        root.left = getRoot(l1, l1 + len - 1, l2, l2 + len - 1);
        root.right = getRoot(l1 + len + 1, r1, l2 + len, r2 - 1);
        return root;
    }
}
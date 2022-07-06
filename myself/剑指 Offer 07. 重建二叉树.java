// 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。

// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。


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
    int n;
    int[] preorder;
    int[] inorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        n = preorder.length;
        this.preorder = preorder;
        this.inorder = inorder;
        return makeTree(0, n - 1, 0, n - 1);
    }
    private TreeNode makeTree(int preLeft, int preRight, int inLeft, int inRight){
        // 如果不合法，返回null节点
        if(isUnvalid(preLeft, preRight, inLeft, inRight)) return null;
        // 当前节点就是前序遍历的第一个值
        int value = preorder[preLeft];
        TreeNode node = new TreeNode(value);
        // 找到当前节点位于中序遍历的位置
        int inIndex = findIndexInOrder(value, inLeft, inRight);
        // 求左、右子树有多少个节点
        int leftLen = inIndex - inLeft;
        int rightLen = inRight - inIndex;
        // 遍历构建左右子树
        node.left = makeTree(preLeft + 1, preLeft + leftLen, inIndex - leftLen, inIndex - 1);
        node.right = makeTree(preLeft + leftLen + 1, preRight, inIndex + 1, inIndex + rightLen);
        return node;
    }
    // 不合法情况：四个参数均超过范围（小于0或者大于等于n），或者左比右大
    private boolean isUnvalid(int a, int b, int c, int d){
        if(a > b || c > d) return true;
        return a < 0 || a >= n || b < 0 || b >= n || c < 0 || c >= n || d < 0 || d >= n;
    }
    // 找到当前节点在中序遍历中的位置
    private int findIndexInOrder(int value, int inLeft, int inRight){
        for(int i = inLeft; i <= inRight; i++){
            if(inorder[i] == value) return i;
        }
        return -1;
    }
}
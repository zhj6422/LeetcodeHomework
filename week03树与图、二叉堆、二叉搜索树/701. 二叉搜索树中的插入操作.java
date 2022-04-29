// 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。

// 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
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

 // 迭代
 // 比较当前节点与val大小，大于则将root向左走，小于则向右走
 // 直到走到叶子结点，根据大小插入即可
 class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode head = root;
        TreeNode pre = root;
        TreeNode tmpNode = new TreeNode(val);
        if(root == null){
            return tmpNode;
        }
        while(root != null){
            pre = root;
            if(root.val > val){
                root = root.left;
            } else {
                root = root.right;
            }
        }
        if(pre.val > val){
            pre.left = tmpNode;
        } else{
            pre.right = tmpNode;
        }
        return head;
    }
}


// 递归，如果为空就新建一个节点，如果不为空就根据大小判断向左还是向右
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        if(root.val < val){
            root.left = insertIntoBST(root.left, val);
        }else{
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
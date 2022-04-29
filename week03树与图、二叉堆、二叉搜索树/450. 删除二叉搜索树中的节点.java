// 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

// 一般来说，删除节点可分为两个步骤：

// 首先找到需要删除的节点；
// 如果找到了，删除它。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
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

 // 递归
 // 1. 参数：当前节点、删除目标值；返回值：当前节点删除后给上一层的子节点
 // 2. 终止条件：当遇到null的时候，返回null
 // 3. 当层逻辑：五种情况
 // 处理五种情况：
 // 1. 找不到key，返回root
 // 2. 找到key，key是叶子结点，直接删除
 // 3. 找到key，key没有左子树，右子树顶替
 // 4. 找到key，key没有右子树，左子树顶替
 // 5. 找到key，key有左右子树
 //    两种方法：
 //       1. 找到key右子树的最左子节点（比key大比其他小的节点），用它和key的值交换，然后删除这个最左子节点
 //       2. 找到key右子树的最左子节点，将key的左子树作为最左子节点的左子树，删除key，key的右子树顶替key
 class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }
        if(root.val < key){
            root.right = deleteNode(root.right, key);
        }else if(root.val > key){
            root.left = deleteNode(root.left, key);
        }else{
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            TreeNode tmp = root.right;
            while(tmp.left != null){
                tmp = tmp.left;
            }
            root.val = tmp.val;
            root.right = deleteNode(root.right, tmp.val);
        }
        return root;
    }
}
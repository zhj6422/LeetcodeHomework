// 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。

// 如果指定节点没有对应的“下一个”节点，则返回null。


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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return getNext(root, p.val);
    }
    // 查找目标节点的后继
    private TreeNode getNext(TreeNode root, int val){
        TreeNode ans = null;
        TreeNode curr = root;
        while(curr != null){
            // 如果找到目标节点，判断是否有右子树，有的话从右子树一路向左，即为后继
            if(curr.val == val){
                if(curr.right != null){
                    curr = curr.right;
                    while(curr.left != null) curr = curr.left;
                    ans = curr;
                }
                break;
            }
            // 根据数值大小继续查找
            if(curr.val > val){
                // 保存查找路径中，比目标节点要大的最小父节点
                if(ans == null || ans.val > curr.val) ans = curr;
                curr = curr.left;
            }else{
                curr = curr.right;
            }
        }
        return ans;
    }
}
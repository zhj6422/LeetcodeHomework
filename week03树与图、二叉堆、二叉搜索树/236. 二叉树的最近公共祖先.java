// 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
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

 // 递归
 // 使用后序遍历，相当于自底向上扫描树
 // 1. 参数：当前节点，查找的节点p、q
 //    返回值：找到p、q，则返回p、q，否则返回null，通过判断是否为null来指示是否查到
 // 2. 终止条件：找到p、q或者节点为null
 // 3. 当层逻辑：
 //    先接住左右子树传来的返回值
 //    如果左右均为null，则返回null
 //    如果左右有一个不为null，则返回那个不为null的返回值
 //    如果左右都不为null，则返回当前的root节点（即找到了最近公共祖先）
 class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q || root == null){
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
    
        if(leftNode != null && rightNode != null){
            return root;
        } else if(leftNode != null && rightNode == null){
            return leftNode;
        } else if(leftNode == null && rightNode != null){
            return rightNode;
        } else{
            return null;
        }
    }
}



// 目的：求包含p又包含q的最小子树的根节点
class Solution {
    private TreeNode p;
    private TreeNode q;
    private TreeNode result;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.p = p;
        this.q = q;
        findAnc(root);
        return result;
    }

    private Boolean[] findAnc(TreeNode node){
        // 第一个bool表示是否包含p，第二个bool表示是否包含q
        Boolean[] resultPair = new Boolean[2];
        Arrays.fill(resultPair, Boolean.FALSE);
        if(node == null) return resultPair;
        Boolean[] leftPair = findAnc(node.left);
        Boolean[] rightPair = findAnc(node.right);
        if(leftPair[0] == true || rightPair[0] == true || node.val == p.val){
            resultPair[0] = true;
        }
        if(leftPair[1] == true || rightPair[1] == true || node.val == q.val){
            resultPair[1] = true;
        }
        // 保存第一次找到时候的节点，这个就是既包含p又包含q的最小子树的根节点
        if(resultPair[0] == true && resultPair[1] == true && result == null) result = node;
        return resultPair;
    }
}
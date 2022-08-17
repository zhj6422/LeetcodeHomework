// 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。

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
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        HashMap<Integer, Integer> resultMap = new HashMap<>();
        q.add(root);
        int layer = 1;
        while(!q.isEmpty()){
            // 当前层有多少个
            int nums = q.size();
            while(nums-- > 0){
                // 遍历当前层
                TreeNode node = q.poll();
                resultMap.put(layer, resultMap.getOrDefault(layer, 0) + node.val);
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
            // 遍历完当前层，layer+1
            layer++;
        }
        return resultMap.get(layer - 1);
    }
}
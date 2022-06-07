// 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。

// 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。

// 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/house-robber-iii
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
    // 对于没有确定大小的情况，使用HashMap存储
    // dp存储某个节点的收益，int数组中，第一个表示不偷的最大收益，第二个表示偷的最大收益
    HashMap<TreeNode, int[]> dp;
    public int rob(TreeNode root) {
        dp = new HashMap<TreeNode, int[]>();
        // 针对访问到叶子结点的子节点的情况
        dp.put(null, new int[]{0, 0});
        dfs(root);
        // 最顶层偷与不偷都可以，返回最大值
        return Math.max(dp.get(root)[0], dp.get(root)[1]);
    }

    // 树，dfs遍历所有情况
    private void dfs(TreeNode node){
        if(node == null) return;
        dp.put(node, new int[2]);
        dfs(node.left);
        dfs(node.right);
        // 自底向上，计算偷与不偷的最大收益
        // 不偷，下一层偷与不偷都可以
        dp.get(node)[0] = Math.max(dp.get(node.left)[0], dp.get(node.left)[1]) + Math.max(dp.get(node.right)[0], dp.get(node.right)[1]);
        // 偷，下一层肯定不能偷
        dp.get(node)[1] = dp.get(node.left)[0] + dp.get(node.right)[0] + node.val;
    }
}
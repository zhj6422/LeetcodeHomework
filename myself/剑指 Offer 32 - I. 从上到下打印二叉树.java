// 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

//  

// 例如:
// 给定二叉树: [3,9,20,null,null,15,7],

//     3
//    / \
//   9  20
//     /  \
//    15   7
// 返回：

// [3,9,20,15,7]

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
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
    public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null) return new int[]{};
        q.add(root);
        ArrayList<Integer> resultList = new ArrayList<>();
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            resultList.add(temp.val);
            if(temp.left != null) q.add(temp.left);
            if(temp.right != null) q.add(temp.right);
        }
        int[] result = new int[resultList.size()];
        int index = 0;
        for(Integer num : resultList){
            result[index++] = num;
        }
        return result;
    }
}
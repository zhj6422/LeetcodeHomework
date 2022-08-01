// 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

//  

// 例如:
// 给定二叉树: [3,9,20,null,null,15,7],

//     3
//    / \
//   9  20
//     /  \
//    15   7
// 返回其层次遍历结果：

// [
//   [3],
//   [20,9],
//   [15,7]
// ]

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


// 在BFS基础上，添加一个反转List的函数，判断是奇数层还是偶数层，根据不同层决定是否对List做反转
// 注意，每次添加到result的时候是开始遍历到下一层的时候，所以最后一层不会在while循环中加入到result中，需要自己处理最后一层

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Queue<HashMap<Integer, TreeNode>> q = new LinkedList<>();
        HashMap<Integer, TreeNode> rootMap = new HashMap<>();
        rootMap.put(1, root);
        q.add(rootMap);
        int layer = 1;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        while(!q.isEmpty()){
            HashMap<Integer, TreeNode> tempMap = q.poll();
            int currLayer = (int)tempMap.keySet().toArray()[0];
            if(currLayer != layer){
                if(currLayer % 2 != 0){
                    temp = reverseList(temp);   
                }
                result.add(temp);
                temp = new ArrayList<>();
                layer = currLayer;
            }
            TreeNode currNode = tempMap.get(currLayer);
            temp.add(currNode.val);
            if(currNode.left != null){
                HashMap<Integer, TreeNode> newMap = new HashMap<>();
                newMap.put(currLayer + 1, currNode.left);
                q.add(newMap);
            }
            if(currNode.right != null){
                HashMap<Integer, TreeNode> newMap = new HashMap<>();
                newMap.put(currLayer + 1, currNode.right);
                q.add(newMap);
            }
        }
        if(temp.size() > 0){
            if((layer + 1) % 2 != 0){
                temp = reverseList(temp);
            }
            result.add(temp);
        }
        return result;
    }
    private List<Integer> reverseList(List<Integer> A){
        int index = A.size() - 1;
        List<Integer> result = new ArrayList<>();
        while(index >= 0){
            result.add(A.get(index--));
        }
        return result;
    }
}



// 大佬解法
// 用双端队列存储中间结果
// 如果是奇数层，则添加到尾部，否则添加到头部
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if(res.size() % 2 == 0) tmp.addLast(node.val); // 偶数层 -> 队列头部
                else tmp.addFirst(node.val); // 奇数层 -> 队列尾部
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }
}
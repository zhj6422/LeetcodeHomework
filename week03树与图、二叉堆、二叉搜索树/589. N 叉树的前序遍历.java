// 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。

// n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/


class Solution {
    List<Integer> result = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        dfs(root);
        return result;
    }
    private void dfs(Node root){
        if(root == null) return;
        result.add(root.val);
        for(Node child : root.children){
            dfs(child);
        }
    }
}



// 迭代的方式实现
class Solution2 {
    List<Integer> result = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        // 用栈来模拟内存栈调用
        Stack<Node> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            result.add(node.val);
            // 需要从后向前压入栈
            for(int i = node.children.size() - 1; i >= 0; i--){
                stack.push(node.children.get(i));
            }
        }
        return result;
    }
    
}
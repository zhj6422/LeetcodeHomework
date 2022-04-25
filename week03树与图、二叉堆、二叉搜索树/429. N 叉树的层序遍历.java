// 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。

// 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。

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
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        // 保存节点以及它所在的层数
        queue.add(new Pair<Node, Integer>(root, 0));
        while(!queue.isEmpty()){
            // 获取节点以及层数
            Node node = queue.peek().getKey();
            int depth = queue.poll().getValue();
            // 如果结果集大小小于层数，要增加一个List来存储节点的值
            if(depth >= result.size()){
                result.add(new ArrayList<Integer>());
            }
            result.get(depth).add(node.val);
            for(Node child : node.children){
                queue.add(new Pair<Node, Integer>(child, depth + 1));
            }
        }
        return result;
    }
}

class Solution2 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Node> que = new LinkedList<Node>();
        if(root == null){
            return result;
        }
        que.offerLast(root);
        while(!que.isEmpty()){
            // 保存当前层的大小，确保新增的List只放当前层的节点
            int size = que.size();
            List<Integer> tmpLayer = new ArrayList<Integer>();
            for (int i = 0; i < size; i++){
                Node tmpNode = que.pollFirst();
                tmpLayer.add(tmpNode.val);
                if(!tmpNode.children.isEmpty()){
                    for(Node childrenNode : tmpNode.children){
                        que.offerLast(childrenNode);
                    }
                }
            }
            result.add(tmpLayer);
        }
        return result;
    }
}
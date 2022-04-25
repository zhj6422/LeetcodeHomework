// 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
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

 // 序列化：按照先序遍历，将节点存入一个字符串中，根节点的子节点保存“null“
 // 反序列化：按照先序遍历构建，遇到null就返回
 public class Codec {

    List<String> serialStr = new ArrayList<String>();

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        serializeStr(root);
        // 用逗号隔开，转化为字符串
        return String.join(",", serialStr);
    }

    private void serializeStr(TreeNode root){
        if(root == null){
            // 根节点的子节点需要保存
            serialStr.add("null");
            return;
        }
        // 先序遍历
        serialStr.add(String.valueOf(root.val));
        serializeStr(root.left);
        serializeStr(root.right);
    }

    private String[] dataSplit; // 保存分割完的先序遍历的字符串
    private int curr; // 保存当前遍历到的数组位置
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 将字符串按照逗号分割为字符串数组
        dataSplit = data.split(",");
        curr = 0;
        TreeNode root = deserialStr();
        return root;
    }

    private TreeNode deserialStr(){
        if(dataSplit[curr].equals("null")){
            curr++;
            return null;
        }
        // 先序遍历，这里要和序列化的时候一样
        TreeNode root = new TreeNode(Integer.parseInt(dataSplit[curr]));
        curr++;
        root.left = deserialStr();
        root.right = deserialStr();
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));










// 层序遍历的方法实现
// 层序遍历，把节点内容存入数组，如果遍历到null就存入“null”，用逗号隔开
public class Codec2 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<String> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node == null){
                result.add("null");
                continue;
            }
            result.add(String.valueOf(node.val));
            queue.add(node.left);
            queue.add(node.right);
        }
        return String.join(",", result);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] serializeStr = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        if(serializeStr[0].equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(serializeStr[0]));
        queue.add(root);
        int curr = 1;
        // 逐层处理，每次处理把节点加到队列中，每次从队头取出来增加左右子节点
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            // 处理左子节点
            if(serializeStr[curr].equals("null")){
                node.left = null;
                curr++;
            }else{
                TreeNode leftNode = new TreeNode(Integer.parseInt(serializeStr[curr]));
                node.left = leftNode;
                queue.add(leftNode);
                curr++;
            }
            // 处理右子节点
            if(serializeStr[curr].equals("null")){
                node.right = null;
                curr++;
            }else{
                TreeNode rightNode = new TreeNode(Integer.parseInt(serializeStr[curr]));
                node.right = rightNode;
                queue.add(rightNode);
                curr++;
            }
        }
        return root;
    }
}

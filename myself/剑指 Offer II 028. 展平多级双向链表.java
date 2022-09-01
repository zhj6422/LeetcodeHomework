// // 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

// // 给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。

// 如何表示测试用例中的多级链表？

// 以 示例 1 为例：

//  1---2---3---4---5---6--NULL
//          |
//          7---8---9---10--NULL
//              |
//              11--12--NULL
// 序列化其中的每一级之后：

// [1,2,3,4,5,6,null]
// [7,8,9,10,null]
// [11,12,null]
// 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。

// [1,2,3,4,5,6,null]
// [null,null,7,8,9,10,null]
// [null,11,12,null]
// 合并所有序列化结果，并去除末尾的 null 。

// [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/Qv1Da2
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

// DFS深度优先搜索遍历
class Solution {
    public Node flatten(Node head) {
        // 对头节点进行dfs，可以得到结果
        dfs(head);
        return head;
    }

    // 深度优先遍历
    private Node dfs(Node head){
        // 记录当前节点，以及它的前一个节点
        Node curr = head;
        Node last = null;
        while(curr != null){
            Node next = curr.next;
            // 如果当前节点孩子不为空，就进入下一层
            if(curr.child != null){
                Node childLast = dfs(curr.child);
                // 拿到下一层返回的最后一个节点之后，处理这一段节点，将下一层插入当前层的curr与next之间
                next = curr.next;
                curr.next = curr.child;
                curr.child.prev = curr;

                if(next != null){
                    childLast.next = next;
                    next.prev = childLast;
                }
                // 最后将当前节点的孩子节点变为null
                curr.child = null;
                // 当前节点要设置为next，而它的前面一个节点是childLast
                last = childLast;
            }else{
                // 孩子为空的话，继续遍历即可
                last = curr;
            }
            curr = next;
        }
        // 返回当前段的最后一个节点，因为前面判断curr为空才停止，这时候最后一个节点是curr的前面一个节点，也就是last
        return last;
    }
}
// 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。

// 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。

// 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。

// 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。

//  

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/4ueAj6
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/


// 链表模拟题
class Solution {
    public Node insert(Node head, int insertVal) {
        Node protect = head;
        // 先创建新节点
        Node newNode = new Node();
        newNode.val = insertVal;
        // 如果为空链，直接返回新节点
        if(head == null){
            newNode.next = newNode;
            return newNode;
        }
        // 遍历一遍链表，找到最小值的节点和最大值的节点
        Node curr = head.next;
        Node minNode = head;
        Node maxNode = head;
        while(curr != head){
            if(curr.val < minNode.val) minNode = curr;
            if(curr.val >= maxNode.val) maxNode = curr; // 找最大值的最后一个，所以要用>=
            curr = curr.next;
        }

        // 如果最大和最小相同，则新节点插入在哪里都一样
        if(minNode.val == maxNode.val){
            newNode.next = protect.next;
            protect.next = newNode;
            return protect;
        }else if(insertVal >= maxNode.val || insertVal <= minNode.val){
            // 如果新节点大于最大或者小于最小，就应该插入在最大节点和最小节点之间
            // 由于前面我们找到了最后一个最大节点，就插入在这个最大节点的后面
            Node first = maxNode.next;
            maxNode.next = newNode;
            newNode.next = first;
        }else{
            // 否则，从最小节点开始遍历，找到合适的插入位置，插入即可
            Node temp = minNode;
            Node pre = null;
            while(temp.val < insertVal){
                pre = temp;
                temp = temp.next;
            }
            pre.next = newNode;
            newNode.next = temp;
        }
        // 返回原来的头节点
        return protect;
    }
}
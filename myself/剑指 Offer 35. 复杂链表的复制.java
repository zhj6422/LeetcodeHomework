// 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node temp = head;
        Node res = new Node(-1);
        Node curr = res;
        int index = 0;
        // map1存新链的节点，以下标作为key
        HashMap<Integer, Node> map1 = new HashMap<>();
        // map2存旧链，根据节点为key，存下标
        HashMap<Node, Integer> map2 = new HashMap<>();
        // 先构建链表，只构建next
        while(temp != null){
            Node tempNode = new Node(temp.val);
            curr.next = tempNode;
            // 将temp放入map2中
            map2.put(temp, index);
            curr = curr.next;
            // 将curr放入map1中
            map1.put(index++, curr);
            temp = temp.next;
        }
        // 回到起点，开始构建random
        curr = res.next;
        temp = head;
        while(temp != null){
            if(temp.random != null){
                // 从map2取index，然后根据index取map1的节点，给新链的节点做下一个random节点
                curr.random = map1.get(map2.get(temp.random));
            }
            temp = temp.next;
            curr = curr.next;
        }
        return res.next;
    }
}


// 大佬解法
// 先用一个Map存老节点-》新节点
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while(cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        // 4. 构建新链表的 next 和 random 指向
        while(cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 5. 返回新链表的头节点
        return map.get(head);
    }
}
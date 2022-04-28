// 给你一个链表数组，每个链表都已经按升序排列。

// 请你将所有链表合并到一个升序链表中，返回合并后的链表。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    private PriorityQueue<Node> pq;
    public ListNode mergeKLists(ListNode[] lists) {
        // 创建优先队列
        pq = new PriorityQueue<>(new NodeComparator());
        for(ListNode node : lists){
            if(node == null) continue;
            pq.add(new Node(node.val, node));
        }
        // head保护节点，存储返回结果
        ListNode head = new ListNode();
        ListNode tail = head;
        while(!pq.isEmpty()){
            // 把最小的点加入结果链表
            Node node = pq.poll();
            tail.next = node.listNode;
            tail = tail.next;
            // 将待处理的链表节点加入优先队列
            ListNode next = node.listNode.next;
            if(next != null){
                pq.add(new Node(next.val, next));
            }
        }
        return head.next;
    }
    // 比较逻辑
    private class NodeComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return a.key - b.key;
        }
    }
    // 自定义Node
    private class Node {
        private int key;
        private ListNode listNode;
        public Node(int key, ListNode listNode){
            this.key = key;
            this.listNode = listNode;
        }
        public int getKey(){
            return this.key;
        }
        public ListNode getListNode(){
            return this.listNode;
        }
    }
}


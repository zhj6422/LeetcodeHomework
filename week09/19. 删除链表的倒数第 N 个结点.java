// 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

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

 // 在head之前添加保护节点，返回的时候，返回保护节点的下一位，解决删除头节点的问题
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode guardNode = new ListNode();
        guardNode.next = head;
        ListNode last = guardNode;
        ListNode first = guardNode;
        // 需要删除的是first的下一个节点，所以，last需要比first提前走n+1步
        for(int i = 0; i <= n; i++){
            last = last.next;
        }
        while(last != null){
            last = last.next;
            first = first.next;
        }
        first.next = first.next.next;
        return guardNode.next;
    
    }
}
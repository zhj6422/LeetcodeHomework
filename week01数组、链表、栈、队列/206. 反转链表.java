// 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 
// https://leetcode-cn.com/problems/reverse-linked-list/

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
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode curr = head;
        ListNode currNext = head.next;
        // 处理中间的过程，保存两个节点的后面一个节点，然后反转这两个节点的指向，然后进入下一次循环
        while(currNext != null){
            ListNode tmp = currNext.next;
            currNext.next = curr;
            curr = currNext;
            currNext = tmp;
        }
        // 处理最终结果的尾部，原来的head变成最终结果的尾
        head.next = null;
        // curr是遍历最后一个非空的节点，也是最终结果的头
        return curr;
    }
}
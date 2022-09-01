// 给定一个单链表 L 的头节点 head ，单链表 L 表示为：

//  L0 → L1 → … → Ln-1 → Ln 
// 请将其重新排列后变为：

// L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

//  

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/LGjMqU
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


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

 /*
  * 用快慢指针将链表分割成两部分
  * 反转后半部分，并将两部分割裂成两个链表
  * 交叉汇总成一条链表
  */
class Solution {
    public void reorderList(ListNode head) {
        ListNode protect1 = new ListNode();
        protect1.next = head;
        ListNode fast = protect1;
        ListNode slow = protect1;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // 如果只有一个节点，slow走一步而已就空了，直接返回
        if(slow.next == null) return;
        // 反转后半部分，并返回后半部分的头节点
        ListNode head2 = reverseList(slow.next);
        slow.next = null;
        // 两条链表，交叉汇总成一条
        ListNode next1 = head.next;
        ListNode next2 = head2.next;
        while(next1 != null && next2 != null){
            head.next = head2;
            head2.next = next1;
            head = next1;
            next1 = head.next;
            head2 = next2;
            next2 = head2.next; 
        }
        // 由于判断条件是next是否为空，最后还需要处理一步
        head.next = head2;
        head2.next = next1;
    }

    // 反转head开头的后续链表，并返回反正后的第一个节点
    private ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode curr = head;
        ListNode next = curr.next;
        while(next != null){
            curr.next = pre;
            pre = curr;
            curr = next;
            next = next.next;
        }
        curr.next = pre;
        return curr;
    }
}
// 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

// k 是一个正整数，它的值小于或等于链表的长度。

// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

// 进阶：

// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
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
 // 分组
 // 每组之间进行反转
 class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode protectNode = new ListNode(0, head);
        ListNode last = protectNode;
        while(head != null){
            // 分组
            ListNode end = findEnd(head, k);
            if(end == null){ // 不存在下一组
                break;
            }
            // 反转组内的k个元素
            ListNode nextHead = end.next;
            reverseNodes(head, nextHead);
            // 处理头和尾
            last.next = end;
            head.next = nextHead;
            //最后改变头和尾，准备下一组的处理
            last = head;
            head = nextHead;
        }
        return protectNode.next;
    }
    // 反转组内的连线（只反转内部，不反转开头和结尾
    private void reverseNodes(ListNode head, ListNode nextHead){
        ListNode last = head;
        ListNode nextNode = last.next;
        while(nextNode != nextHead){
            ListNode tmp = nextNode.next;
            nextNode.next = last;
            last = nextNode;
            nextNode = tmp;
        }
    }
    // 找到一组的最后一个节点
    private ListNode findEnd(ListNode node, int k){
        while(node != null){
            k--;
            if(k == 0){
                return node;
            }
            node = node.next;
        }
        return null;
    }
}
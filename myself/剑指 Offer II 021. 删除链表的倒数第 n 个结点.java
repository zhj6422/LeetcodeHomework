// 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

//  

// 示例 1：

// 输入：head = [1,2,3,4,5], n = 2
// 输出：[1,2,3,5]
// 示例 2：

// 输入：head = [1], n = 1
// 输出：[]
// 示例 3：

// 输入：head = [1,2], n = 1
// 输出：[1]

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/SLwz0R
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
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode protect = new ListNode();
        protect.next = head;

        ListNode end = protect;
        ListNode start = protect;
        // 需要再往前一步，需要找到的是倒数第n+1个，然后删除它的后一个
        n++;
        while(n-- > 0){
            end = end.next;
        }
        while(end != null){
            start = start.next;
            end = end.next;
        }
        start.next = start.next.next;
        return protect.next;
        
    }
}

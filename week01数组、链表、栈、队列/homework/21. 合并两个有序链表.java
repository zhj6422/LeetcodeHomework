// 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 创建结果的头节点
        ListNode curr = new ListNode();
        ListNode protectNode = curr;
        while(list1 != null && list2 != null){
            // 取小的
            if(list1.val <= list2.val){
                curr.next = list1;
                list1 = list1.next;
                curr = curr.next;
            }else{
                curr.next = list2;
                list2 = list2.next;
                curr = curr.next;
            }
        }
        // 当其中一个list处理完了，处理剩下的list
        if(list1 == null){
            curr.next = list2;
        }else{
            curr.next = list1;
        }
        return protectNode.next;
    }
}

// 两种情况：一种是list1的节点加入result，一种是list2的节点加入result
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode curr = new ListNode();
        ListNode protectNode = curr;
        while(list1 != null || list2 != null){
            // 如果list1为空，或者list2不为空（list1也不为空）但是list2当前节点的值小于list1当前节点的值
            if(list1 == null || (list2 != null && list2.val < list1.val)){ // list2加入result
                curr.next = list2;
                list2 = list2.next;
                curr = curr.next;
            }else{ // list1加入result
                curr.next = list1;
                list1 = list1.next;
                curr = curr.next;
            }
        }
        return protectNode.next;
    }
}
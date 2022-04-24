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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        ListNode result = merge(lists, 0, lists.length - 1);
        return result;
    }

    private ListNode merge(ListNode[] lists, int l, int r){
        // 当左右相等的时候，只剩下一个List，直接返回
        if(l == r){
            return lists[l];
        }

        int m = l + (r - l) / 2;
        ListNode result = new ListNode(); // 保护节点
        ListNode curr = result;
        
        // 分治
        ListNode left = merge(lists, l, m);
        ListNode right = merge(lists, m+1, r);
        
        // 当层处理逻辑
        while(left != null && right != null){
            if(left.val <= right.val){
                curr.next = left;
                left = left.next;
                curr = curr.next;
            }else{
                curr.next = right;
                right = right.next;
                curr = curr.next;
            }
        }
        if(left == null){
            curr.next = right;
        }else{
            curr.next = left;
        }
        return result.next;
    }
}
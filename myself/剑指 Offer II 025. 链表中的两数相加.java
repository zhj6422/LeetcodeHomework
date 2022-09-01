// 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

// 可以假设除了数字 0 之外，这两个数字都不会以零开头。

//  

// 示例1：



// 输入：l1 = [7,2,4,3], l2 = [5,6,4]
// 输出：[7,8,0,7]
// 示例2：

// 输入：l1 = [2,4,3], l2 = [5,6,4]
// 输出：[8,0,7]
// 示例3：

// 输入：l1 = [0], l2 = [0]
// 输出：[0]

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/lMSNwu
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



// 暴力用栈
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> res = new Stack<>();
        while(l1 != null){
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            s2.push(l2.val);
            l2 = l2.next;
        }
        boolean isAddOne = false;
        while(!s1.isEmpty() && !s2.isEmpty()){
            int curr1 = s1.pop();
            int curr2 = s2.pop();
            int tempRes = isAddOne ? curr1 + curr2 + 1 : curr1 + curr2;
            if(tempRes >= 10){
                tempRes -= 10;
                isAddOne = true;
            }else{
                isAddOne = false;
            }
            res.push(tempRes);
        }
        Stack<Integer> left;
        if(s1.isEmpty()) left = s2;
        else left = s1;
        while(!left.isEmpty()){
            if(isAddOne){
                int temp = left.pop();
                temp = isAddOne? temp + 1: temp;
                if(temp >= 10){
                    temp -= 10;
                    isAddOne = true;
                }else{
                    isAddOne = false;
                }
                res.push(temp);
            }else{
                res.push(left.pop());
            }
        }
        if(isAddOne){
            res.push(1);
        }
        ListNode protect = new ListNode();
        ListNode currNode = protect;
        while(!res.isEmpty()){
            ListNode temp = new ListNode(res.pop());
            currNode.next = temp;
            currNode = temp;
        }
        
        return protect.next;
    }
}



// 大佬们的双栈
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer>stack1=new LinkedList<>();
        Deque<Integer>stack2=new LinkedList<>();
        while (l1!=null){
            stack1.push(l1.val);
            l1=l1.next;
        }
        while (l2!=null){
            stack2.push(l2.val);
            l2=l2.next;
        }
        ListNode newHead=new ListNode(-1);
        int add=0;
        while (!stack1.isEmpty()||!stack2.isEmpty()||add!=0){
            int sum=add;
            sum+=stack1.isEmpty()?0:stack1.poll();
            sum+=stack2.isEmpty()?0:stack2.poll();
            add=sum/10;
            ListNode cur=new ListNode(sum%10);
            cur.next=newHead.next;
            newHead.next=cur;
        }
        return newHead.next;
    }
}


// 其他思路：
// 1. 反转链表
// 2. 两数之和
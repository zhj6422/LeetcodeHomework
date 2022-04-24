#### [811. 子域名访问计数](https://leetcode-cn.com/problems/subdomain-visit-count/)

```java
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> count = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();
        for(String cpdomain: cpdomains){
            int countNum = getCountNum(cpdomain);
            String tmpStr = cpdomain.substring(cpdomain.indexOf(" ")+1);
            while(!tmpStr.equals("")){
                int tmpCount = 0;
                if(count.containsKey(tmpStr)){
                    tmpCount = count.get(tmpStr);
                }
                tmpCount += countNum;
                count.put(tmpStr, tmpCount);
                if(tmpStr.indexOf(".") == -1){
                    tmpStr = "";
                    continue;
                }
                tmpStr = getNextStr(tmpStr);
            }
        }
        for(Map.Entry<String, Integer> entry: count.entrySet()){
            String ans = entry.getValue() + " " + entry.getKey();
            result.add(ans);
        }
        return result;
    }
    private String getNextStr(String s){
        int index = s.indexOf(".");
        return s.substring(index + 1);
    }

    private int getCountNum(String s){
        int index = s.indexOf(" ");
        String countStr = s.substring(0, index);
        int result = Integer.parseInt(countStr);
        return result;
    }
}
```





#### [697. 数组的度](https://leetcode-cn.com/problems/degree-of-an-array/)

```java
class Solution {
    public int findShortestSubArray(int[] nums) {
        int len = nums.length, maxCount = 0, minWindow = 0;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int[] pair = map.get(nums[i]);
            if (pair == null) {
                pair = new int[]{i, 1};
                map.put(nums[i], pair);
            } else {
                pair[1]++;
            }
            if (pair[1] > maxCount) {
                maxCount = pair[1];
                minWindow = i - pair[0] + 1;
            } else if (pair[1] == maxCount) {
                minWindow = Math.min(minWindow, i - pair[0] + 1);
            }
        }
        for(Map.Entry<Integer, int[]> entry: map.entrySet()){
            System.out.println(entry.getKey() + ": {" + entry.getValue()[0] + "," + entry.getValue()[1] + "}");
        }
        return minWindow; 

    }
}
```







#### [23. 合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

```java
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
        if(l == r){
            return lists[l];
        }
        int m = l + (r - l) / 2;
        ListNode result = new ListNode();
        ListNode curr = result;
        
        ListNode left = merge(lists, l, m);
        ListNode right = merge(lists, m+1, r);
        
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
```


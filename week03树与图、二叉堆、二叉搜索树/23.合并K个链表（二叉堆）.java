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
 // 自定义二叉堆实现
 // 二叉堆实现功能：最小的存放在堆顶，用数组作为存储的数据结构
 // 取出：只能取堆顶的数值
 // 删除：将数组最后一个元素移到根节点位置，然后逐层向下比较，从自身、左子节点、右子节点中取一个最小的和父节点交换
 // 插入：在数组的最后进行插入，然后逐层向上和父节点比较，比父节点小就和父节点交换
 class Solution {
    private BinaryHeap bh;
    public ListNode mergeKLists(ListNode[] lists) {
        bh = new BinaryHeap();
        for(ListNode node : lists){
            if(node == null) continue;
            bh.insert(new Node(node.val, node));
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while(!bh.isEmpty()){
            Node node = bh.getMin();
            bh.deleteMin();
            tail.next = node.listNode;
            tail = tail.next;
            ListNode next = node.listNode.next;
            if(next != null){
                bh.insert(new Node(next.val, next));
            }
        }
        return head.next;
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

    // 自定义二叉堆
    private class BinaryHeap{
        // 用变长数组来存储
        private ArrayList<Node> heap;

        public BinaryHeap(){
            this.heap = new ArrayList<>();
            this.heap.add(new Node(0, null));
        }

        public boolean isEmpty(){
            return heap.size() == 1;
        }

        public Node getMin(){
            return heap.get(1);
        }

        public void deleteMin(){
            // 将最后一个节点移到根位置
            heap.set(1, heap.get(heap.size() - 1));
            // 去掉最后一个节点
            heap.remove(heap.size() - 1);
            // 向下查找
            heapifyDown(1);
        }

        // 插入到最后，然后向上查找
        public void insert(Node node){
            heap.add(node);
            heapifyUp(heap.size() - 1);
        }

        // 向上查找，如果父节点比自己大，就和父节点交换
        private void heapifyUp(int p){
            while(p > 1){
                int fa = p / 2;
                if(heap.get(fa).key > heap.get(p).key){
                    swap(p, fa);
                    p = p / 2;
                }else{
                    break;
                }
            }
        }

        // 向下查找，从p点开始，如果子节点比自己小，将较小者和当前节点交换
        private void heapifyDown(int p){
            // child为要交换的子节点
            int child = 2 * p;
            while(child < heap.size()){
                int other = child + 1;
                if(other < heap.size() && heap.get(other).key < heap.get(child).key){ // 如果other（另一个节点）更小，让child等于它
                    child = other;
                }
                // 如果child比父节点小，就交换，否则，结束查找
                if(heap.get(child).key < heap.get(p).key){
                    swap(child, p);
                    // 向下一层，父节点是这一层的child
                    p = child;
                    child = child * 2;
                }else {
                    break;
                }
            }
        }

        // 交换逻辑
        private void swap(int p, int q){
            Node tmp = heap.get(p);
            heap.set(p, heap.get(q));
            heap.set(q, tmp);
        }

    }
}


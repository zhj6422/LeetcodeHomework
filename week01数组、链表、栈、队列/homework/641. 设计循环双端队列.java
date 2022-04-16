// 设计实现双端队列。

// 实现 MyCircularDeque 类:

// MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
// boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
// boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
// boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
// boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
// int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
// int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
// boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
// boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。

// 自定义ListNode链表节点
class ListNode{
    public int val;
    public ListNode next;
    public ListNode pre;
    // 3个构造函数
    public ListNode(){
        this.val = 0;
        this.next = null;
        this.pre = null;
    }
    public ListNode(int val){
        this.val = val;
    }
    public ListNode(int val, ListNode next, ListNode pre){
        this.val = val;
        this.next = next;
        this.pre = pre;
    }
}

class MyCircularDeque {
    private int maxSize; // 记录队列的最大长度
    private int size; // 记录队列当前的长度
    private ListNode head = new ListNode(); // 头保护节点
    private ListNode tail = new ListNode(); // 尾保护节点
    

    public MyCircularDeque(int k) {
        this.maxSize = k;
        head.next = tail;
        tail.pre = head;
    }

    public boolean insertFront(int value) {
        if(this.size >= this.maxSize){
            System.out.println("队列已满");
            return false;
        }else{
            ListNode tmp = new ListNode(value);
            tmp.pre = head;
            tmp.next = head.next;
            head.next.pre = tmp;
            head.next = tmp;
            size++;
            System.out.println("插入头部成功");
            return true;
        }
    }
    
    public boolean insertLast(int value) {
        if(this.size >= this.maxSize){
            System.out.println("队列已满");
            return false;
        }else{
            ListNode tmp = new ListNode(value);
            tmp.pre = tail.pre;
            tmp.next = tail;
            tail.pre.next = tmp;
            tail.pre = tmp;
            size++;
            System.out.println("插入尾部成功");
            return true;
        }
    }
    
    public boolean deleteFront() {
        if(this.size == 0){
            System.out.println("队列已空");
            return false;
        }else{
            head.next.next.pre = head;
            head.next = head.next.next;
            size--;
            System.out.println("删除头部成功");
            return true;
        }
    }
    
    public boolean deleteLast() {
        if(this.size == 0){
            System.out.println("队列已空");
            return false;
        }else{
            tail.pre.pre.next = tail;
            tail.pre = tail.pre.pre;
            size--;
            System.out.println("删除尾部成功");
            return true;
        }
    }
    
    public int getFront() {
        if(size != 0){
            return head.next.val;
        }else{
            return -1;
        }
    }
    
    public int getRear() {
        if(size != 0){
            return tail.pre.val;
        }else{
            return -1;
        }
    }
    
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isFull() {
        if(size == maxSize){
            return true;
        }else{
            return false;
        }
    }
}



/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
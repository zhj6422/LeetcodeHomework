// 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

// 实现 MinStack 类:

// MinStack() 初始化堆栈对象。
// void push(int val) 将元素val推入堆栈。
// void pop() 删除堆栈顶部的元素。
// int top() 获取堆栈顶部的元素。
// int getMin() 获取堆栈中的最小元素。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/min-stack
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 用两个栈，一个栈存放当前的元素，另一个栈来维护当前的最小值
class MinStack {
    private Stack<Integer> s;
    private Stack<Integer> minValue;

    public MinStack() {
        this.s = new Stack();
        this.minValue = new Stack();
    }
    
    public void push(int val) {
        s.push(val);
        if(minValue.empty()) minValue.push(val);
        else minValue.push(Math.min(minValue.peek(), val));
    }
    
    public void pop() {
        s.pop();
        minValue.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return minValue.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
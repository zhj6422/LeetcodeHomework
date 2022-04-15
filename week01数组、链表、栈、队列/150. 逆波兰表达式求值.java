// 根据 逆波兰表示法，求表达式的值。

// 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

// 注意 两个整数之间的除法只保留整数部分。

// 可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。



// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 坑点：Java中String比较不能用==，需要用equals方法
// String转换为Integer：Integer.parseInt(String)
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        for(String token : tokens){
            if(token.equals("+")  || token.equals("-") || token.equals("*") || token.equals("/")){
                int y = stack.pop();
                int x = stack.pop();
                int z = calculatea(x, y, token);
                stack.push(z);
            }else{
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }
    private int calculatea(int x, int y, String op){
        if(op.equals("+")) return x + y;
        if(op.equals("-")) return x - y;
        if(op.equals("*")) return x * y;
        if(op.equals("/")) return x / y;
        return 0;
    }
}
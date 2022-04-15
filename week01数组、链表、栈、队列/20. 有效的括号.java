// 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

// 有效字符串需满足：

// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。


// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/valid-parentheses
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for(int i = 0; i < s.length(); i++){
            char currChar = s.charAt(i);
            if(currChar == '(' || currChar == '[' || currChar == '{') stack.push(currChar);
            else{
                if(stack.empty()) return false;
                char peekChar = stack.peek();
                if(currChar == ')' && peekChar != '(') return false;
                if(currChar == ']' && peekChar != '[') return false;
                if(currChar == '}' && peekChar != '{') return false;
                stack.pop();
            }
        }
        if(stack.empty()) return true;
        return false;
    }
}
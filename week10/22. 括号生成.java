// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

class Solution {
    String s = "";
    ArrayList<String> result = new ArrayList<>();
    int n;
    public List<String> generateParenthesis(int n) {
        this.n = n;
        dfs(0, 0, 0);
        return result;
    }
    private void dfs(int i, int left, int right){
        if(left > n || right > n || !isValid(s)) return;
        if(i == 2 * n){
            result.add(s);
            return;
        }
        s += "(";
        dfs(i + 1, left + 1, right);
        s = s.substring(0, s.length()-1);
        s += ")";
        dfs(i + 1, left, right + 1);
        s = s.substring(0, s.length()-1);
    }

    private boolean isValid(String s){
        int left = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') left++;
            else{
                if(left <= 0) return false;
                left--;
            }
        }
        return true;
    }
}
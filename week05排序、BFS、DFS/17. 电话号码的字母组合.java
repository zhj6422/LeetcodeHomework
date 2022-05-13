// 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    private String digits;
    List<String> result = new ArrayList<>();
    Map<Character, String> alpha = new HashMap<>();
    String temp = "";
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return result;
        this.digits = digits;
        alpha.put('2',"abc");
        alpha.put('3',"def");
        alpha.put('4',"ghi");
        alpha.put('5',"jkl");
        alpha.put('6',"mno");
        alpha.put('7',"pqrs");
        alpha.put('8',"tuv");
        alpha.put('9',"wxyz");
        dfs(0);
        return result;
    }
    // 深度优先搜索，需要记录的状态：每一轮的index以及当前拼接成的字符串temp
    private void dfs(int index){
        if(index == digits.length()){
            result.add(temp);
            return;
        }
        String lettersForIndex = alpha.get(digits.charAt(index));
        for(int i = 0; i < lettersForIndex.length(); i++){
            // 每次添加temp
            temp += lettersForIndex.charAt(i);
            dfs(index + 1);
            // 还原现场
            temp = temp.substring(0, temp.length() - 1);
        }
    }
}
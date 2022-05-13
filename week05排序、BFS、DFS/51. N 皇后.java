// n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/n-queens
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 斜对角不能有重复的皇后：通过（横坐标-纵坐标）和（横坐标+纵坐标）是否使用过来判断
class Solution {
    private int nums;
    private List<Integer> temp;
    private boolean[] used;
    private List<List<Integer>> ans;
    private Map<Integer, Boolean> usedMinus;
    private Map<Integer, Boolean> usedPlus;
    public List<List<String>> solveNQueens(int n) {
        nums = n;
        temp = new ArrayList<>();
        used = new boolean[n];
        ans = new ArrayList<>();
        usedMinus = new HashMap<>();
        usedPlus = new HashMap<>();
        dfs(0);
        List<List<String>> result = new ArrayList<List<String>>();
        for (List<Integer> p : ans) {
            List<String> pattern = new ArrayList<String>();
            for (int row = 0; row < n; row++) {
                StringBuilder str = new StringBuilder(".".repeat(n));
                str.setCharAt(p.get(row), 'Q');
                pattern.add(str.toString());
            }
            result.add(pattern);
        }
        return result;
    }
    private void dfs(int row){
        if(row == nums){
            // 加入结果，注意这里要重新new一个List，深拷贝
            // 如果是ans.add(temp); 加入的只是指向temp的引用，temp改变，ans里面保存的结果随之改变
            ans.add(new ArrayList<Integer>(temp));
        }
        for(int col = 0; col < nums; col++){
            if(!used[col] && !usedMinus.getOrDefault(row - col, false) && !usedPlus.getOrDefault(row + col, false)){
                temp.add(col);
                usedMinus.put(row - col, true);
                usedPlus.put(row + col, true);
                used[col] = true;
                dfs(row + 1);
                temp.remove(temp.size() - 1);
                usedMinus.put(row - col, false);
                usedPlus.put(row + col, false);
                used[col] = false;
            }
        }
    }
}
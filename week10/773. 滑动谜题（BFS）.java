// 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.

// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。

// 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。

//  

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/sliding-puzzle
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    HashMap<String, Integer> depth = new HashMap<>();
    Queue<String> queue = new LinkedList<>();
    public int slidingPuzzle(int[][] board) {
        String start = zip(board);
        String target = "123450";
        queue.add(start);
        depth.put(start, 0);
        while(!queue.isEmpty()){
            String s = queue.poll();
            int pos = findZeroPos(s);
            if(pos <= 2) expand(s, pos, pos + 3);
            if(pos >= 3) expand(s, pos - 3, pos);
            if(pos % 3 != 0) expand(s, pos - 1, pos);
            if(pos % 3 != 2) expand(s, pos, pos + 1);
            if(depth.containsKey(target)) return depth.get(target);
        }
        return -1;
    }
    private void expand(String s, int index, int other){
        char[] ns = s.toCharArray();
        swap(ns, index, other);
        String nextS = new String(ns);
        if(depth.containsKey(nextS)) return;
        depth.put(nextS, depth.get(s) + 1);
        queue.add(nextS);
    }
    private void swap(char[] ns, int x, int y){
        char temp = ns[x];
        ns[x] = ns[y];
        ns[y] = temp;
    }
    private int findZeroPos(String s){
        for(int i = 0; i < 6; i++){
            if(s.charAt(i) == '0') return i;
        }
        return -1;
    }
    private String zip(int[][] board){
        String result = "";
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                result += board[i][j];
            }
        }
        return result;
    }
}
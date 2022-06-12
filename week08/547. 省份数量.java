// 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

// 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

// 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。

// 返回矩阵中 省份 的数量。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/number-of-provinces
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    int[] fa;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        fa = new int[n];
        for(int i = 0; i < n; i++){
            fa[i] = i;
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(isConnected[i][j] == 1) unionSet(i, j);
            }
        }
        int result = 0;
        for(int i = 0; i < n; i++){
            if(find(i) == i) result++;
        }
        return result;
    }

    private int find(int i){
        if(fa[i] == i) return i;
        return fa[i] = find(fa[i]);
    }

    private void unionSet(int i, int j){
        int x = find(i);
        int y = find(j);
        if(x != y) fa[x] = y;
    }
}
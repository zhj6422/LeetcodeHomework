// 树可以看成是一个连通且 无环 的 无向 图。

// 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。

// 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。



// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/redundant-connection
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int[] fa;
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // 初始化并查集
        fa = new int[n + 1];
        for(int i = 0; i <= n; i++) fa[i] = i;
        int[] result = new int[2];
        // 遍历edges，如果边两端点不属于并查集，加入集合，如果都属于并查集，表示这条边冗余
        for(int i = 0; i < n; i++){
            int x = edges[i][0];
            int y = edges[i][1];
            int xFa = find(x);
            int yFa = find(y);
            if(xFa == yFa) result = edges[i];
            unionSet(xFa, yFa);
        }
        return result;
    }
    // 并查集查找
    private int find(int i){
        if(fa[i] == i) return i;
        // 优化：将结果赋值给fa[i]，优化并查集查找速度
        return fa[i] = find(fa[i]);
    }
    // 加入集合
    private void unionSet(int i, int j){
        int x = find(i);
        int y = find(j);
        if(x != y) fa[x] = y;
    }
}
// 树可以看成是一个连通且 无环 的 无向 图。

// 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。

// 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/redundant-connection
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    int n; // 存最大的节点编号
    ArrayList<ArrayList<Integer>> edgesList;
    boolean[] isVisited;
    boolean hasCircle;
    public int[] findRedundantConnection(int[][] edges) {
        // 求n
        for(int[] edge: edges){
            int x = edge[0];
            int y = edge[1];
            n = Math.max(Math.max(n, x), y);
        }
        edgesList = new ArrayList<>();
        isVisited = new boolean[n+1];
        for(int i = 0; i <= n; i++){
            edgesList.add(new ArrayList<Integer>());
            isVisited[i] = false;
        }
        
        hasCircle = false;
        for(int[] edge: edges){
            int x = edge[0];
            int y = edge[1];
            // 无向图，两个方向都存
            edgesList.get(x).add(y);
            edgesList.get(y).add(x);

            // 每加入一条边检测一下是否构成环
            // 还原isVisited，不受处理上一条边的影响
            for(int i = 0; i < isVisited.length; i++){
                isVisited[i] = false;
            }
            dfs(x, -1);
            // 如果加入当前这条边，出现有环的情况，就返回这条边
            if(hasCircle) return edge;
        }
        return null;
    }
    // 深度优先搜索，查看是否有环，用isVisited数组来存储被访问过的节点情况，如果访问到之前被遍历过的节点表示有环
    private void dfs(int x, int fa){
        isVisited[x] = true;
        for(Integer y: edgesList.get(x)){
            if(y == fa) continue;
            else if(isVisited[y]) hasCircle = true;
            else dfs(y, x);
        }
    }
}
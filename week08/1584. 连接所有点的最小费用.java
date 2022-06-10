// 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。

// 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。

// 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。



// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/min-cost-to-connect-all-points
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int[] fa;
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(j);
                temp.add(Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]));
                edges.add(temp);
            }
        }
        Collections.sort(edges, (ArrayList<Integer> a, ArrayList<Integer> b) -> {
            return a.get(2) - b.get(2);
        });
        fa = new int[n];
        for(int i = 0; i < n; i++) fa[i] = i;
        int result = 0;
        for(ArrayList<Integer> edge : edges){
            int x = edge.get(0);
            int y = edge.get(1);
            int z = edge.get(2);
            x = find(x);
            y = find(y);
            if(x != y){
                result += z;
                fa[x] = y;
            }
        }
        return result;
    }

    private int find(int x){
        if(x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }
}
// 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。

// 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。

// 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // dist作为邻边矩阵，dist[i][j]为点i到点j的最短距离
        int[][] dist = new int[n][n];
        // 初始化为无穷大
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dist[i][j] = (int)1e9;
            }
        }
        // 自己到自己为0
        for(int i = 0; i < n; i++) dist[i][i] = 0;
        // 对题目给的条件修改边与边之间直接相连的距离
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            int z = edge[2];
            dist[x][y] = z;
            dist[y][x] = z;
        }
        // Floyd算法，得到点与点之间的最短距离
        // 注意：k的循环要在最外层，保证处理dist[i][j]时，dist[i][k]与dist[k][j]已经处理完成
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        // 统计结果
        int minNeighbour = (int)1e9;
        int index = 0;
        for(int i = 0; i < n; i++){
            int neighbour = 0;
            for(int j = 0; j < n; j++){
                if(i != j && dist[i][j] <= distanceThreshold){
                    neighbour++;
                }
            }
            if(neighbour < minNeighbour || (neighbour == minNeighbour && i > index)){
                minNeighbour = neighbour;
                index = i;
            }
        }
        return index;
    }
}
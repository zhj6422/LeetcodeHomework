// 有 n 个网络节点，标记为 1 到 n。

// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。

// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。



// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/network-delay-time
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


// Bellman-Ford算法
class Solution {
    int[] dist;
    public int networkDelayTime(int[][] times, int n, int k) {
        // dist存储到每个点的距离
        dist = new int[n + 1];
        for(int i = 0; i <= n; i++){
            dist[i] = (int)1e9;
        }
        dist[k] = 0;
        // 最多只需要n-1轮即可完成求得每个点的最短距离
        for(int round = 1; round <= n - 1; round++){
            // flag用于提前结束，如果没有更新，表示已经完成
            boolean flag = false;
            for(int[] edge : times){
                int x = edge[0];
                int y = edge[1];
                int z = edge[2];
                // 如果到y的点从当前边出发距离更短，更新dist[y]
                if(dist[x] + z < dist[y]){
                    dist[y] = dist[x] + z;
                    flag = true;
                }
            }
            // 如果没有更新了，提前结束
            if(!flag) break;
        }
        // 已经求完所有最短路径，题目要求找多久才能到达所有节点，所以找里面的最大值
        int result = 0;
        for(int i = 1; i <= n; i++){
            result = Math.max(result, dist[i]);
        }
        // 如果存在无穷，表示有的点到达不了，返回-1
        return result == 1e9 ? -1 : result;
    }
}
// 有 n 个网络节点，标记为 1 到 n。

// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。

// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。



// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/network-delay-time
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.PriorityQueue;
import java.util.Comparator;
class Solution {
    int[] dist;
    boolean[] expand;
    // 每个点出边的终点
    ArrayList<ArrayList<Integer>> points = new ArrayList<>();
    // 每个点出边的权值
    ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
    public int networkDelayTime(int[][] times, int n, int k) {
        dist = new int[n + 1];
        for(int i = 0; i <= n; i++) dist[i] = (int)1e9;
        dist[k] = 0;
        expand = new boolean[n + 1];
        // 维护出边数组
        for(int i = 0; i <= n; i++){
            ArrayList temp1 = new ArrayList<>();
            ArrayList temp2 = new ArrayList<>();
            points.add(temp1);
            edges.add(temp2);
        }
        for(int[] t : times){
            int x = t[0];
            int y = t[1];
            int z = t[2];
            points.get(x).add(y);
            edges.get(x).add(z);
        }

        // 优先队列存储多个数组，每个数组第一个数为位置y，第二个数为从k到达y的距离dist
        PriorityQueue<ArrayList<Integer>> q = new PriorityQueue<>(new CustomComparator());
        ArrayList first = new ArrayList<Integer>(Arrays.asList(k, dist[k]));
        q.add(first);
        while(!q.isEmpty()){
            ArrayList<Integer> temp = q.poll();
            int x = temp.get(0);
            if(expand[x]) continue;
            for(int i = 0; i < points.get(x).size(); i++){
                int y = points.get(x).get(i);
                int z = edges.get(x).get(i);
                if(dist[x] + z < dist[y]){
                    dist[y] = dist[x] + z;
                    ArrayList newOne = new ArrayList<Integer>(Arrays.asList(y, dist[y]));
                    q.add(newOne);
                }
            }
            expand[x] = true;
        }

        int result = 0;
        for(int i = 1; i <= n; i++){
            result = Math.max(result, dist[i]);
        }
        return result == 1e9 ? -1 : result;

    }
}

class CustomComparator implements Comparator<ArrayList<Integer>> {

    @Override
    public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
        int value = o2.get(1) - o1.get(1);
        //元素以相反的顺序排序
        if (value > 0) return -1;
        else if (value < 0) return 1;
        else return 0;
    }
}
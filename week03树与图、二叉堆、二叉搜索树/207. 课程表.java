// 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/course-schedule
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 用队列来遍历节点，入度为0（表示已经没有需要先修的课程）的节点进入队列
// 存储图的数据，并构造一个inDeg数组，表示各个节点的入度
class Solution {
    List<List<Integer>> edges;
    int n;
    int[] inDeg;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        n = numCourses;
        inDeg = new int[n];
        for(int i = 0; i < n; i++){
            edges.add(new ArrayList<Integer>());
        }
        // 存储图，并初始化入度数组inDeg
        for(int[] pre : prerequisites){
            // 想要修x，必须先修y，y指向x
            int x = pre[0];
            int y = pre[1];
            edges.get(y).add(x);
            inDeg[x]++;
        }
        return find();
    }

    private boolean find(){
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[n]; // 用于存储拓扑结果
        int m = 0; // 用来记录当前遍历了多少个
        // 将入度为0的课程加入队列，这些课程可以直接上，不需要修其他课程
        for(int i = 0; i < n; i++){
            if(inDeg[i] == 0) queue.add(i);
        }
        while(!queue.isEmpty()){
            Integer x = queue.poll();
            result[m++] = x;
            for(int y : edges.get(x)){
                inDeg[y]--;
                if(inDeg[y] == 0) queue.add(y);
            }
        }
        if(m == n) return true;
        return false;
    }
}
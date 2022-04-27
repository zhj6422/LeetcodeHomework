// 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/course-schedule-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 思路同第207题一致，返回结果不同
class Solution {
    List<List<Integer>> edges;
    int n;
    int[] inDeg;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        n = numCourses;
        edges = new ArrayList<>();
        inDeg = new int[n];
        for(int i = 0; i < n; i++){
            edges.add(new ArrayList<>());
        }
        for(int[] pre : prerequisites){
            int x = pre[0];
            int y = pre[1];
            edges.get(y).add(x);
            inDeg[x]++;
        }
        return find();
    }

    private int[] find(){
        int[] result = new int[n];
        int m = 0;
        Queue<Integer> queue = new LinkedList<>();
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
        if(m == n) return result;
        return new int[0];
    }
}
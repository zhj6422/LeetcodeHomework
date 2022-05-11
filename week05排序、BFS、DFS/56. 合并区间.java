// 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/merge-intervals
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int[][] merge(int[][] intervals) {
        // 先按照区间左侧坐标排序，如果一样，按照区间右侧坐标排序
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });
        int furthest = -1;
        int start = -1;
        List<int[]> result = new ArrayList<>();
        for(int[] interval : intervals){
            int left = interval[0];
            int right = interval[1];
            if(left <= furthest){
                // 左坐标包含在前面的区间中，扩大范围
                furthest = Math.max(right, furthest);
            }else{
                // 开启了一段新的区间，先把前面区间保存到结果
                if(furthest != -1){
                    result.add(new int[]{start, furthest});
                }
                furthest = right;
                start = left;
            }
        }
        // 保存最后一个区间
        result.add(new int[]{start, furthest});
        int[][] ans = result.toArray(new int[result.size()][]);
        return ans;
    }
}
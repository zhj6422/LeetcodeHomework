class Solution {
    // 差分
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> events = new ArrayList<>();
        // 遍历数组，左坐标保存1，右坐标的下一位保存-1
        for(int[] interval: intervals){
            events.add(new int[]{interval[0], 1});
            events.add(new int[]{interval[1] + 1, -1});
        }
        // 排序，先按照坐标排序，如果坐标一样，-1在前
        //（因为之前保存的是右坐标的下一位为-1）
        // 如果保存的是右坐标为-1，这里要保证1在前-1在后
        Collections.sort(events, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });
        // 统计结果
        int count = 0;
        int start = -1;
        ArrayList<int[]> result = new ArrayList<>();
        for(int[] event: events){
            // 如果加之前就是0，那么当前遍历的位置为开始坐标
            if(count == 0) start = event[0];
            count += event[1];
            // 加之后变成0，表示一段区间结束，放入结果（注意：把右坐标-1）
            if(count == 0) result.add(new int[]{start, event[0] - 1});
        }
        return result.toArray(new int[result.size()][]);
    }
}
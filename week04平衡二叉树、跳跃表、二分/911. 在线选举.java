// 给你两个整数数组 persons 和 times 。在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。

// 对于发生在时刻 t 的每个查询，需要找出在 t 时刻在选举中领先的候选人的编号。

// 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。

// 实现 TopVotedCandidate 类：

// TopVotedCandidate(int[] persons, int[] times) 使用 persons 和 times 数组初始化对象。
// int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/online-election
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class TopVotedCandidate {
    private int[] persons;
    private int[] times;
    private Map<Integer, Integer> candidateMap;
    private List<int[]> timeList;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.persons = persons;
        this.times = times;
        // 存储候选人获得的票数
        this.candidateMap = new HashMap<>();
        // 存储一个int数组，数组中第一个为时刻time，第二个为该时刻的领先候选人
        this.timeList = new ArrayList<>();
        // 存储最大的票数
        int val = 0;
        for(int i = 0; i < times.length; i++){
            // 更新候选人票数
            candidateMap.put(persons[i], candidateMap.getOrDefault(persons[i], 0) + 1);
            if (candidateMap.get(persons[i]) >= val) { 
                // 如果大于最大票数，表示这个时刻候选人领先，更新最大票数以及list数组
                val = candidateMap.get(persons[i]);
                timeList.add(new int[]{times[i], persons[i]});
            }
        }
    }
    
    public int q(int t) {
        // 根据时间来二分
        int l = 0, r = timeList.size() - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if(timeList.get(mid)[0] <= t){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        return timeList.get(r)[1];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
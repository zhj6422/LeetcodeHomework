// 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。

// 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。

// 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
// 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。

// 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。

// 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// BFS实现
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        char[] chars = {'A', 'C', 'G', 'T'};
        // 用于存储有效的基因序列
        Set<String> set = new HashSet<>(); 
        for(String s: bank){
            set.add(s);
        }
        // 存储某个基因序列需要多少次变化
        Map<String, Integer> map = new HashMap<>();
        // 使用队列实现BFS
        Queue<String> q = new LinkedList<>();
        q.add(start);
        map.put(start, 0);
        while(!q.isEmpty()){
            int size = q.size();
            // 每次搜索一层
            while(size-- > 0){
                String s = q.poll();
                char[] cs = s.toCharArray();
                int step = map.get(s);
                // 对当前的状态的每个位做一个修改，作为下一个状态，考虑是否能形成有效基因序列
                for (int i = 0; i < 8; i++) {
                    for (char c : chars) {
                        // 相同则跳过
                        if (cs[i] == c) continue;
                        // 改变一个位置
                        char[] clone = cs.clone();
                        clone[i] = c;
                        String sub = String.valueOf(clone);
                        // 如果不是有效基因，跳过
                        if (!set.contains(sub)) continue;
                        // 如果已经搜索过了，跳过
                        if (map.containsKey(sub)) continue;
                        // 找到结果了，在当前改变次数上再加一次，返回结果
                        if (sub.equals(end)) return step + 1;
                        // 还没找到结果，但是满足有效基因序列，保存
                        map.put(sub, step + 1);
                        q.add(sub);
                    }
                }
            }
        }
        return -1;
    }
}
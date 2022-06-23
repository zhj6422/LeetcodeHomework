// 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：

// 每一对相邻的单词只差一个字母。
//  对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
// sk == endWord
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/word-ladder
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    HashSet<String> wordSet = new HashSet<>();
    HashMap<String, Integer> depthBegin = new HashMap<>();
    HashMap<String, Integer> depthEnd = new HashMap<>();
    Queue<String> qBegin = new LinkedList<String>();
    Queue<String> qEnd = new LinkedList<String>();
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for(String s: wordList) wordSet.add(s);
        if(!wordSet.contains(endWord)) return 0;
        // 开始单词深度为1
        depthBegin.put(beginWord, 1);
        depthEnd.put(endWord, 1);
        // BFS
        qBegin.add(beginWord);
        qEnd.add(endWord);
        int currDepth = 1;
        while(!qBegin.isEmpty() || !qEnd.isEmpty()){
            while (!qBegin.isEmpty() && depthBegin.get(qBegin.peek()) == currDepth) {
                int res = expand(qBegin, depthBegin, depthEnd);
                if(res != -1) return res;
            }
            while (!qEnd.isEmpty() && depthEnd.get(qEnd.peek()) == currDepth) {
                int res = expand(qEnd, depthEnd, depthBegin);
                if (res != -1) return res; 
            }
            currDepth++;
        }
        return 0;
    }


    private int expand(Queue<String> q, HashMap<String, Integer> depth, HashMap<String, Integer> depthOther){
        if(q.isEmpty()) return -1;
        String s = q.poll();
        for(int i = 0; i < s.length(); i++){
            for(char ch = 'a'; ch <= 'z'; ch++){
                // 如果相同，跳过
                if(ch == s.charAt(i)) continue;
                // 修改第i个字符为其他字母，保存到ns
                String ns = s.substring(0, i) + ch + s.substring(i + 1);
                // wordList中不存在当前单词，不要
                if(!wordSet.contains(ns)) continue;
                // 如果已经存在，算过了不要
                if(depth.containsKey(ns)) continue;
                // System.out.println("1: " + depth);
                // System.out.println("2: "+depthOther);
                // System.out.println("ns: " + ns + " s: " + s);
                if(depthOther.containsKey(ns)) return depth.get(s) + depthOther.get(ns);
                depth.put(ns, depth.get(s) + 1);
                q.add(ns);
            }
        }
        return -1;
    }
}
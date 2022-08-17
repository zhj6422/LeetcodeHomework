// 输入一个字符串，打印出该字符串中字符的所有排列。

//  

// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

//  

// 示例:

// 输入：s = "abc"
// 输出：["abc","acb","bac","bca","cab","cba"]

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    //存放结果
    List<String> result = new ArrayList<>();
    //暂存结果
    List<Character> path = new ArrayList<>();
    public String[] permutation(String s) {
        char[] c = s.toCharArray();
        boolean[] used = new boolean[c.length];
        Arrays.sort(c);
        bt(c, used);
        String[] res = new String[result.size()];
        int i = 0;
        for(String str : result){
            res[i++] = str;
        }
        return res;
    }

    private void bt(char[] c, boolean[] used){
        // 终止条件：当path长度达到c大小，保存结果
        if(path.size() == c.length) {
            StringBuilder builder = new StringBuilder(path.size());
            for(Character ch: path){
                builder.append(ch);
            }
            result.add(builder.toString());
            return;
        }
        for(int i = 0; i < c.length; i++){
            // 如果和前一个相同，且前面那个数在当前分支中没有被用过（表示在同一层有被其他分支用过），跳过
            if(i > 0 && c[i - 1] == c[i] && used[i - 1] == false) continue;
            // 如果没有使用过，则标记为使用，进入下一层
            if(!used[i]){
                used[i] = true;
                path.add(c[i]);
                bt(c, used);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }

    }
}
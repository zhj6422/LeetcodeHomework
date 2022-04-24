// 网站域名 "discuss.leetcode.com" 由多个子域名组成。顶级域名为 "com" ，二级域名为 "leetcode.com" ，最低一级为 "discuss.leetcode.com" 。当访问域名 "discuss.leetcode.com" 时，同时也会隐式访问其父域名 "leetcode.com" 以及 "com" 。

// 计数配对域名 是遵循 "rep d1.d2.d3" 或 "rep d1.d2" 格式的一个域名表示，其中 rep 表示访问域名的次数，d1.d2.d3 为域名本身。

// 例如，"9001 discuss.leetcode.com" 就是一个 计数配对域名 ，表示 discuss.leetcode.com 被访问了 9001 次。
// 给你一个 计数配对域名 组成的数组 cpdomains ，解析得到输入中每个子域名对应的 计数配对域名 ，并以数组形式返回。可以按 任意顺序 返回答案。

//  

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/subdomain-visit-count
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        // 用于统计每个域名出现的次数
        Map<String, Integer> count = new HashMap<>();
        // 用于存放结果
        ArrayList<String> result = new ArrayList<>();
        for(String cpdomain: cpdomains){
            int countNum = getCountNum(cpdomain);
            String tmpStr = cpdomain.substring(cpdomain.indexOf(" ")+1);
            while(!tmpStr.equals("")){
                int tmpCount = 0;
                if(count.containsKey(tmpStr)){
                    tmpCount = count.get(tmpStr);
                }
                tmpCount += countNum;
                count.put(tmpStr, tmpCount);
                // 如果已经不包含“.”了，表示已经处理完最后一个域名，需要结束
                if(tmpStr.indexOf(".") == -1){
                    tmpStr = "";
                    continue;
                }
                tmpStr = getNextStr(tmpStr);
            }
        }
        for(Map.Entry<String, Integer> entry : count.entrySet()){
            String ans = entry.getValue() + " " + entry.getKey();
            result.add(ans);
        }
        return result;
    }

    // 根据.来分割获取下一个字符串
    private String getNextStr(String s){
        int index = s.indexOf(".");
        return s.substring(index + 1);
    }

    // 获取数字
    private int getCountNum(String s){
        int index = s.indexOf(" ");
        String countStr = s.substring(0, index);
        int result = Integer.parseInt(countStr);
        return result;
    }
}
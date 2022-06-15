//  给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。

// 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/jewels-and-stones
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> jewelsSet = new HashSet<>();
        for(int i = 0; i < jewels.length(); i++){
            jewelsSet.add(jewels.charAt(i));
        }
        int result = 0;
        for(int i = 0; i < stones.length(); i++){
            if(jewelsSet.contains(stones.charAt(i))) result++;
        }
        return result;
    }
}
// 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/longest-consecutive-sequence
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    // 使用Map来作为并查集
    HashMap<Integer, Integer> fa = new HashMap<>();
    public int longestConsecutive(int[] nums) {
        // 对nums进行排序，确保并入并查集的序号为相同者（最小的数）
        Arrays.sort(nums);
        int n = nums.length;
        // 初始化并查集
        for(int i = 0; i < n; i++) fa.put(nums[i], nums[i]);
        // 对于所有nums，如果比他小1的数属于一个集合，则加入这个集合
        for(int i = 0; i < n; i++){
            if(fa.get(nums[i] - 1) != null){
                unionSet(nums[i], fa.get(nums[i] - 1));
            }
        }
        // 保存各个集合的长度
        HashMap<Integer, Integer> lenMap = new HashMap<>();
        int result = 0;
        // 遍历并查集所有集合，统计所有集合长度，并在这期间维护最大的长度写入result中
        for(HashMap.Entry <Integer, Integer> entrySet : fa.entrySet()){
            int key = entrySet.getKey();
            int value = entrySet.getValue();
            if(lenMap.get(value) != null){
                lenMap.put(value, lenMap.get(value) + 1);
            }else{
                lenMap.put(value, 1);
            }
            if(lenMap.get(value) > result) result = lenMap.get(value);
        }
        return result;
    }
    // 并查集find、unionSet方法
    private int find(int i){
        if(fa.get(i) == i) return i;
        int result = find(fa.get(i));
        fa.put(i, result);
        return result;
    }
    private void unionSet(int i, int j){
        int x = find(i);
        int y = find(j);
        if(x != y) fa.put(x, y);
    }
}
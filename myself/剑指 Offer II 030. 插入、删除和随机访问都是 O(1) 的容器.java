// 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：

// insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
// remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
// getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
//  

// 示例 :

// 输入: inputs = ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
// [[], [1], [2], [2], [], [1], [2], []]
// 输出: [null, true, false, true, 2, true, false, 2]
// 解释:
// RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
// randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入

// randomSet.remove(2); // 返回 false，表示集合中不存在 2 

// randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2] 

// randomSet.getRandom(); // getRandom 应随机返回 1 或 2 
  
// randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2] 

// randomSet.insert(2); // 2 已在集合中，所以返回 false 

// randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 
//  

// 提示：

// -231 <= val <= 231 - 1
// 最多进行 2 * 105 次 insert ， remove 和 getRandom 方法调用
// 当调用 getRandom 方法时，集合中至少有一个元素

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/FortPu
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


// 模拟题，用哈希表存放数据及其位置，用数组存放数据
class RandomizedSet {

    // 数组用于存每个位置的数
    int[] nums;
    // key为数据，value为他们的位置
    HashMap<Integer, Integer> map;
    // 从-1开始
    int index;
    Random r = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.nums = new int[20010];
        this.map = new HashMap<>();
        this.index = -1;
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        // 如果不存在，插入map中，并更新nums数组情况，更新index
        map.put(val,++index);
        nums[index] = val;
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        // 存在，要删除这个元素，获取他的位置
        int idx = map.remove(val);
        if(idx != index){
            // 如果位置不是最后一个，需要替换，将最后一个替换到被删除的这个位置
            // 将最后一个数的位置index设置成删除的数的位置idx
            map.put(nums[index], idx);
        }
        // 更新nums数组，数组更新index--，不用去删掉里面的数据，反正新增的时候会覆盖
        nums[idx] = nums[index--];
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        // 获取一个当前范围内的随机值，从nums中拿出来即可
        int idx = r.nextInt(index + 1);
        return nums[idx];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
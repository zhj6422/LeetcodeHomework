// 给你一个数组 nums ，请你完成两类查询。

// 其中一类查询要求 更新 数组 nums 下标对应的值
// 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
// 实现 NumArray 类：

// NumArray(int[] nums) 用整数数组 nums 初始化对象
// void update(int index, int val) 将 nums[index] 的值 更新 为 val
// int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
//  

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/range-sum-query-mutable
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class NumArray {
    int n;
    int[] a;
    int[] c;
    public NumArray(int[] nums) {
        n = nums.length;
        a = new int[n + 1];
        c = new int[n + 1];
        for(int i = 1; i <= n; i++){
            a[i] = nums[i - 1];
            add(i, a[i]);
        }
    }
    private int lowbit(int x){
        return x & -x;
    }

    private int query(int x){
        int result = 0;
        for(; x > 0; x -= lowbit(x)) result += c[x];
        return result;
    }

    private void add(int x, int delta){
        for(; x <= n; x += lowbit(x)) c[x] += delta;
    }
    
    public void update(int index, int val) {
        index++;
        add(index, val - a[index]);
        a[index] = val;
    }
    
    public int sumRange(int left, int right) {
        left++;
        right++;
        return query(right) - query(left - 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
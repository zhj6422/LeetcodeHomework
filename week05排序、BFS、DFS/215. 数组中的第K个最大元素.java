// 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 遍历整个长度，返回第n-k位置的数
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }
    // 快排
    private int quickSort(int[] nums, int l, int r, int target){
        if(l >= r) return nums[l];
        int pivot = partition(nums, l, r);
        if(pivot < target){
            // 目标在右，从pivot的右边开始
            return quickSort(nums, pivot + 1, r, target);
        }else{
            return quickSort(nums, l, pivot, target);
        }
    }
    private int partition(int[] nums, int l, int r){
        int pivot = l + (int)(Math.random() * (r - l + 1));
        int pivotVal = nums[pivot];
        while (l <= r) {
            while (nums[l] < pivotVal) l++; 
            while (nums[r] > pivotVal) r--; 
            if (l == r) break; 
            if (l < r) {
                int temp = nums[l]; 
                nums[l] = nums[r]; 
                nums[r] = temp;
                l++; 
                r--;
            } 
        } 
        return r;
    }
}
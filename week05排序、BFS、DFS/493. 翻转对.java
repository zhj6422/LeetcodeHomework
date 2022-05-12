// 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。

// 你需要返回给定数组中的重要翻转对的数量。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/reverse-pairs
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 归并排序，在过程中计算翻转对的个数
// 分成两部分（左、右）之后，总个数= 左内部个数 + 右内部个数 + 跨左右的个数
// 跨左右的个数，左右都排好序，遍历左边，右边的指针向右递增即可（因为左侧指针向右递增之后，右边前缀部分肯定满足）
class Solution {
    private int result = 0;
    public int reversePairs(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return result;
    }

    private void mergeSort(int[] nums, int l, int r){
        if(l >= r) return;
        int mid = l + (r - l ) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        // 对左右排好序的两部分，求翻转对个数
        cal(nums, l, mid, r);
        merge(nums, l, mid, r);
    }

    // 求翻转对个数，随着左边指针向右移动，右侧前缀肯定满足结果
    private void cal(int[] nums, int l, int mid, int r){
        int j = mid;
        for(int i = l; i <= mid; i++){
            while(j < r && nums[i] > 2 * nums[j + 1]) j++;
            result += j - mid;
        }
    }

    private void merge(int[] nums, int l, int mid, int r){
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1;
        for(int k = 0; k < temp.length; k++){
            if(j > r || (i <= mid && nums[i] <= nums[j])){
                temp[k] = nums[i++];
            }else{
                temp[k] = nums[j++];
            }
        }
        for(int k = 0; k < temp.length; k++){
            nums[l + k] = temp[k];
        }
    }
}
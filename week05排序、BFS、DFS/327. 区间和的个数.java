// 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。

// 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/count-of-range-sum
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    // 求前缀和数组sum，区间（i，j）的和等于sum[j] - sum[i]
    // 求满足条件的区间和个数，其实就是求sum[j] - sum[i]满足条件的情况有多少种，可以对sum进行排序，在排序的过程中求一共有多少对（i，j）满足情况
    public int countRangeSum(int[] nums, int lower, int upper) {
        long temp = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            temp += nums[i];
            sum[i + 1] = temp;
        }
        return sortAndCount(sum, lower, upper, 0, sum.length - 1);
    }
    // 对前缀和数组使用归并排序，统计左内部+右内部+左右之间
    private int sortAndCount(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            // 得到左右两侧内部有多少种满足条件
            int leftCount = sortAndCount(sum, lower, upper, left, mid);
            int rightCount = sortAndCount(sum, lower, upper, mid + 1, right);
            int count = leftCount + rightCount;

            // 统计左右两侧共有多少种情况满足条件
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                count += r - l;
                i++;
            }
            merge(sum, left, mid, right);
            return count;
        }
    }

    private void merge(long[] sum, int left, int mid, int right) {
        long[] tempSum = new long[right - left + 1];
        int p1 = left, p2 = mid + 1;
        int p = 0;
        while (p1 <= mid || p2 <= right) {
            if (p1 > mid) tempSum[p++] =sum[p2++];
            else if (p2 > right) tempSum[p++] = sum[p1++];
            else {
                if (sum[p1] < sum[p2]) tempSum[p++] = sum[p1++];
                else tempSum[p++] = sum[p2++];
            }
        }
        for (int j = 0; j < tempSum.length; j++) {
            sum[left + j] = tempSum[j];
        }
    }
}

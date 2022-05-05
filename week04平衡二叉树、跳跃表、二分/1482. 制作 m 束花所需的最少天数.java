// 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。

// 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。

// 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。

// 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int theLast = 0;
        for(int bloom: bloomDay){
            theLast = Math.max(bloom, theLast);
        }
        // 二分求出最佳天数
        int left = 0, right = theLast + 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(isOK(bloomDay, m, k, mid)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        if(right == theLast + 1) return -1;
        return right;
    }
    // 判断天数为day的时候，是否可以满足
    private boolean isOK(int[] bloomDay, int m, int k, int day){
        int curr = 0; // 当前收集了几朵花
        int bloomNum = 0; // 能完成多少束
        for(int i = 0; i < bloomDay.length; i++){
            if(bloomDay[i] <= day){
                curr++;
                if(curr == k) {
                    bloomNum++;
                    curr = 0;
                }
            }else{
                curr = 0;
            }
        }
        if(bloomNum >= m) return true;
        return false;
    }
}
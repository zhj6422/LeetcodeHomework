// 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。

// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

// 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for(int w: weights){
            left = Math.max(left, w);
            right += w;
        }
        while(left < right){
            int mid = (left + right) / 2;
            if(isOK(weights, days, mid)) right = mid;
            else left = mid + 1;
        }
        return right;
    }
    // 判断容量为size的船是否能够满足要求
    private boolean isOK(int[] weights, int days, int size){
        int curr = 0, time = 1;
        for(int w : weights){
            if(curr + w <= size) curr += w;
            else{
                time++;
                curr = w;
            }
        }
        if(time <= days) return true;
        return false;
    }
}
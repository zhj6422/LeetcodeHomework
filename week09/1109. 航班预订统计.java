// 这里有 n 个航班，它们分别从 1 到 n 进行编号。

// 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。

// 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/corporate-flight-bookings
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 差分处理问题，题目针对数组的某一个区间进行操作

class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 差分数组 0 - n + 1，保留最后一位的下一位，避免越界处理
        int[] delta = new int[n + 2];
        for(int[] booking : bookings){
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];
            delta[first] += seats;
            delta[last + 1] -= seats;
        }
        //前缀和数组
        int[] sum = new int[n + 1];
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + delta[i];
        }
        int[] result = new int[n];
        for(int i = 1; i <= n; i++){
            result[i - 1] = sum[i];
        }
        return result;
    }
}
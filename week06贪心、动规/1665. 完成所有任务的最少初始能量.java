// 给你一个任务数组 tasks ，其中 tasks[i] = [actuali, minimumi] ：

// actuali 是完成第 i 个任务 需要耗费 的实际能量。
// minimumi 是开始第 i 个任务前需要达到的最低能量。
// 比方说，如果任务为 [10, 12] 且你当前的能量为 11 ，那么你不能开始这个任务。如果你当前的能量为 13 ，你可以完成这个任务，且完成它后剩余能量为 3 。

// 你可以按照 任意顺序 完成任务。

// 请你返回完成所有任务的 最少 初始能量。



// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/minimum-initial-energy-to-finish-tasks
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
* 假设每个任务为[a, m]，a表示需要耗费的能量，m表示需要的门槛
* 所有任务为[a0, m0],[a1, m1],……
* 假设完成所有任务需要能量最少为S
* S > m0
* S - a0 > m1
* ……
* S - a0 - a1 - …… - a(i-1) > m(i)
* S - a0 - a1 - …… - a(i-1) - a(i) > m(i+1)
* => S > a0 + a1 + …… + a(i-1) + m(i) = p(i)
* => S > a0 + a1 + …… + a(i-1) + a(i) + m(i+1) = p(i+1)
* 假设交换i与i+1两个任务
* 变成
* S - a0 - a1 - …… - a(i-1) > m(i+1)
* S - a0 - a1 - …… - a(i-1) - a(i+1) > m(i)
* => S > a0 + a1 + …… + a(i-1) + m(i+1) = p'(i)
* => S > a0 + a1 + …… + a(i-1) + a(i+1) + m(i) = p'(i+1)
* 如果改变后更小了，表示为 max(p(i), p(i+1)) > max(p'(i), p'(i+1))
* 即p(i+1) > p'(i+1)【另外两项比这两项要小】
* => a(i) + m(i+1) > a(i+1) + m(i)
* => a(i) - m(i) > a(i+1) - m(i+1)
* 得证如果a - m越小，越能获得最小的初始能量
 */
class Solution {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks,(int[] a, int[] b) -> {
            return a[0] - a[1] - (b[0] - b[1]);
        });
        int result = 0;
        for(int i = tasks.length - 1; i >= 0; i--){
            result = Math.max(tasks[i][1], result + tasks[i][0]);
        }
        return result;
    }
}
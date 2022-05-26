// 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

// 你可以认为每种硬币的数量是无限的。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/coin-change
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] opt = new int[amount+1];
        opt[0] = 0;
        for(int i = 1; i <= amount; i++){
            opt[i] = (int)1e9;
            for(int j = 0; j < coins.length; j++){
                if(i - coins[j] >= 0){
                    opt[i] = Math.min(opt[i], opt[i - coins[j]] + 1);
                }
            }
        }
        if(opt[amount] >= (int)1e9) return -1;
        return opt[amount];
    }
}
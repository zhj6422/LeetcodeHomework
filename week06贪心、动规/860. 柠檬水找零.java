// 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。

// 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。

// 注意，一开始你手头没有任何零钱。

// 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。

//  

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/lemonade-change
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> billMap = new HashMap<>();
        billMap.put(5, 0);
        billMap.put(10, 0);
        billMap.put(20, 0);
        for(int bill: bills){
            if(bill == 5){
                billMap.put(5, billMap.get(5) + 1);
            }else if(bill == 10){
                if(billMap.get(5) <= 0){
                    return false;
                }else{
                    billMap.put(5, billMap.get(5) - 1);
                    billMap.put(10, billMap.get(10) + 1);
                }
            }else if(bill == 20){
                if(billMap.get(10) != 0 && billMap.get(5) != 0){
                    billMap.put(20, billMap.get(20) + 1);
                    billMap.put(5, billMap.get(5) - 1);
                    billMap.put(10, billMap.get(10) - 1);
                }else if(billMap.get(5) >= 3){
                    billMap.put(20, billMap.get(20) + 1);
                    billMap.put(5, billMap.get(5) - 3);
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
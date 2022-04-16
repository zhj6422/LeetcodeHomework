
class Solution {
    public int[] plusOne(int[] digits) {
        // 遍历数组
        for(int i = digits.length - 1; i >= 0; i--){
            if(digits[i] < 9){
                digits[i]++;
                break;
            }else{ // 进位
                digits[i] = 0;
            }
        }
        if(digits[0] == 0){ // 第一位也需要进位
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for(int i = 0; i < digits.length; i++){
                result[i+1] = digits[i];
            }
            return result;
        }
        return digits;
    }
}
// 给你两个数组，arr1 和 arr2，arr2 中的元素各不相同，arr2 中的每个元素都出现在 arr1 中。

// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/relative-sort-array
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 计数排序
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 计数排序记录数据个数的数组
        int[] count = new int[1001];
        // 存放结果
        int[] result = new int[arr1.length];
        for(int val : arr1){
            count[val]++;
        }
        int index = 0; // 用于写result数组的当前位置
        // 先把arr2的数据放到结果中
        for(int val : arr2){
            while(count[val] > 0){
                result[index++] = val;
                count[val]--;
            }
        }
        // 再放其他数据
        for(int i = 0; i <= 1000; i++){
            while(count[i] > 0){
                result[index++] = i;
                count[i]--;
            }
        }
        return result;
    }
}
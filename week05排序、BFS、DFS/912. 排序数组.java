// 给你一个整数数组 nums，请你将该数组升序排列。

class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    // 快排AC
    private void quickSort(int[] nums, int l, int r){
        if(l >= r) return;
        int pivot = partition(nums, l, r);
        quickSort(nums, l, pivot);
        quickSort(nums, pivot + 1, r);
    }
    private int partition(int[] nums, int l, int r){
        int pivot = l + (int)(Math.random() * (r - l + 1));
        int pivotVal = nums[pivot];
        while (l <= r) {
            while (nums[l] < pivotVal) l++; 
            while (nums[r] > pivotVal) r--; 
            if (l == r) break; 
            if (l < r) {
                int temp = nums[l]; 
                nums[l] = nums[r]; 
                nums[r] = temp;
                l++; 
                r--;
            } 
        } 
        return r;
    }
    // 归并排序AC
    private void mergeSort(int[] nums, int l, int r){
        if(l >= r) return;
        int mid = (l + r) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
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
    // 堆排序AC
    private int[] heapSort(int[] nums){
        // 优先队列实现堆排序
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i < nums.length; i++){
            q.add(nums[i]);
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = q.poll();
        }
        printNums(nums);
        return nums;
    }
    // 冒泡排序超时
    private int[] bubbleSort(int[] nums){
        for(int j = 0; j < nums.length - 1; j++){
            for(int i = 0; i < nums.length - j - 1; i++){
                if(nums[i] > nums[i+1]){
                    swap(nums, i, i+1);
                }
            }
            printNums(nums);
        }
        return nums; 
    }
    // 插入排序超时
    private int[] insertionSort(int[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            int j = i + 1;
            for(int z = 0; z <= i; z++){
                if(nums[z] > nums[j]){
                    // 在z的位置插入j的值，后面依次向后移动
                    insert(nums, z, j);
                }
            }
            // printNums(nums);
        }
        return nums;
    }
    // 在z的位置插入j的值，后面依次向后移动
    private void insert(int[] nums, int z, int j){
        // 保存j位置的值
        int temp = nums[j];
        int i = j;
        // 移动z-j之间的数据
        while(i > z){
            nums[i] = nums[i - 1];
            i--;
        }
        // 将j数据放入z位置
        nums[z] = temp;
    }
    // 选择排序超时
    private int[] selectionSort(int[] nums){
        for(int i = -1; i < nums.length - 1; i++){
            int min = i + 1;
            for(int j = i + 1; j < nums.length; j++){
                if(nums[min] > nums[j]){
                    min = j;
                }
            }
            swap(nums, i + 1, min);
            // printNums(nums);
        }
        return nums;
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void printNums(int[] nums){
        for(int k = 0; k < nums.length; k++){
            System.out.print(nums[k] + " ");
        }
        System.out.println();
    }
}
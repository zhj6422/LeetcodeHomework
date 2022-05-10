class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> arr2orders = new HashMap<>();
        for(int i = 0; i < arr2.length; i++){
            arr2orders.put(arr2[i], i);
        }
        // Comparator<T> 需要传入对象，不能是原生类型int，需要转为Integer
        // Integer[] arr1Copy = new Integer[arr1.length];
        // for(int i = 0; i < arr1.length; i++){
        //     arr1Copy[i] = arr1[i];
        // }
        Integer[] arr1Copy = Arrays.stream(arr1).boxed().toArray(Integer[]::new);
        Arrays.sort(arr1Copy, (a, b) -> {
            int xPos = arr2orders.containsKey(a) ? arr2orders.get(a) : arr2.length;
            int yPos = arr2orders.containsKey(b) ? arr2orders.get(b) : arr2.length;
            if(xPos == yPos) return a - b;
            else return xPos - yPos;
        });
        // 将结果恢复成int，arr1存放结果
        // for(int i = 0; i < arr1Copy.length; i++){
        //     arr1[i] = arr1Copy[i];
        // }
        arr1 = Arrays.stream(arr1Copy).mapToInt(Integer::valueOf).toArray();
        return arr1;
    }
}
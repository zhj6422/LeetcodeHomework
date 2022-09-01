// 给定一个二维矩阵 matrix，以下类型的多个请求：

// 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
// 实现 NumMatrix 类：

// NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
// int sumRegion(int row1, int col1, int row2, int col2) 返回左上角 (row1, col1) 、右下角 (row2, col2) 的子矩阵的元素总和。

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/O4NDxx
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class NumMatrix {
    // 存储二维数组前缀和
    private int[][] pre_sum_matrix;

    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        // 前面多加一行一列全0，避免溢出判断
        pre_sum_matrix = new int[n + 1][m + 1];
        pre_sum_matrix[1][1] = matrix[0][0];
        // 维护前缀和二维数组
        for(int i = 1; i < n; i++){
            pre_sum_matrix[i+1][1] = pre_sum_matrix[i][1] + matrix[i][0];
        }
        for(int i = 1; i < m; i++){
            pre_sum_matrix[1][i+1] = pre_sum_matrix[1][i] + matrix[0][i];
        }
        for(int i = 2; i <= n; i++){
            for(int j = 2; j <= m; j++){
                /**  
                前缀和二维数组
                    A  B
                    C  ？
                    ？为D位置，等于C + B - A + matrix[D]
                 */
                pre_sum_matrix[i][j] = pre_sum_matrix[i-1][j] + pre_sum_matrix[i][j-1] - pre_sum_matrix[i-1][j-1] + matrix[i-1][j-1];
            }
        }
        // for(int i = 0; i <= n; i++){
        //     for(int j = 0; j <= m; j++){
        //         System.out.print(pre_sum_matrix[i][j]+ " ");
        //     }
        //     System.out.println();
        // }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        /**
            A  C
            D  B
         */
        int A = pre_sum_matrix[row1][col1];
        int B = pre_sum_matrix[row2+1][col2+1];
        int C = pre_sum_matrix[row1][col2+1];
        int D = pre_sum_matrix[row2+1][col1];

        return B - C - D + A;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
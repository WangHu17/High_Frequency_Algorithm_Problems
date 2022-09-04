/**
 * @author wanghu
 * @discrption： 最小的第k个数
 * 给定一个每一行有序、每一列也有序，整体可能无序的二维数组，在给定一个正数k，返回二维数组中最小的第k个数。
 * @create 2022-09-04 11:29
 */
public class P86_KthSmallestElementInSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[N - 1][M - 1];
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) >> 1;
            int[] less = lessThanMid(matrix, mid);
            if (less[0] < k) {
                left = mid + 1;
            } else {
                res = less[1];
                right = mid - 1;
            }
        }
        return res;
    }

    public int[] lessThanMid(int[][] matrix, int val) {
        int N = matrix.length;
        int M = matrix[0].length;
        int row = 0;
        int col = M-1;
        int near = Integer.MIN_VALUE;
        int num = 0;
        while (row < N && col >= 0) {
            if (matrix[row][col] <= val) {
                near = Math.max(near, matrix[row][col]);
                num += col + 1;
                row++;
            }else {
                col--;
            }
        }
        return new int[]{num, near};
    }

}

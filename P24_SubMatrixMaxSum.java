/**
 * @author wanghu
 * @discrption： 子矩阵最大累加和
 * @create 2022-08-08 11:06
 */
public class P24_SubMatrixMaxSum {

    public int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sum = new int[M];
            for (int j = i; j < N; j++) {
                int cur = 0;
                for (int k = 0; k < M; k++) {
                    sum[k] += m[j][k];
                    cur += sum[k];
                    max = Math.max(max, cur);
                    if (cur < 0) cur = 0;
                }
            }
        }
        return max;
    }

}

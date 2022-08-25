import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 俄罗斯套娃问题
 * 给一个二维数组，每一个数组表示一个娃的长和高，只有长和高都大于才能套进去，求最多能套几层。
 * @create 2022-08-25 10:41
 */
public class P50_EnvelopesProblem {

    public int maxEnvelopes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        Arrays.sort(matrix, (o1, o2) -> o1[0] != o2[0] ? (o1[0] - o2[0]) : (o2[1] - o1[1]));
        int N = matrix.length;
        int[] ends = new int[N];
        ends[0] = matrix[0][1];
        int right = 0;
        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = (l + r) / 2;
                if (ends[m] < matrix[i][1]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = matrix[i][1];
        }
        return right + 1;
    }

}

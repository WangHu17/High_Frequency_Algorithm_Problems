import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 6、最长递增链
 * 给定一个二维数组matrix，你可以从任何位置出发，走向上下左右四个方向，返回能走出来的最长的递增链长度。
 * @create 2022-08-01 14:21
 */
public class P6_LongestIncreasingPath {

    public int longestIncreasingPath(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] dp = new int[M][N];
        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(process(matrix, i, j, dp), ans);
            }
        }
        return ans;
    }

    public int process(int[][] m, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j];
        int ans = 0;
        int up = i > 0 && m[i][j] < m[i - 1][j] ? process(m, i - 1, j, dp) : 0;
        int down = i < m.length - 1 && m[i][j] < m[i + 1][j] ? process(m, i + 1, j, dp) : 0;
        int left = j > 0 && m[i][j] < m[i][j - 1] ? process(m, i, j - 1, dp) : 0;
        int right = j < m[0].length - 1 && m[i][j] < m[i][j + 1] ? process(m, i, j + 1, dp) : 0;
        ans = Math.max(Math.max(down, up), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }

    @Test
    public void test(){
        int[][] m = {{7,8,9,2},
                     {9,10,9,8},
                     {1,2,3,7},
                     {2,5,4,5}};
        System.out.println(longestIncreasingPath(m));
    }

}

/**
 * @author wanghu
 * @discrption： 给定两个字符串S和T，返回S的所有子序列中，有多少个子序列的字面值等于T
 * @create 2022-09-04 16:20
 */
public class P87_DistinctSubseqEqualT {

    public int numDistinct(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int N = s1.length;
        int M = s2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = s1[i] == s2[0] ? (dp[i - 1][0] + 1) : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i, M - 1); j++) {
                dp[i][j] = dp[i - 1][j];
                if (s1[i] == s2[j]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[N - 1][M - 1];
    }

}

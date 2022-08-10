/**
 * @author wanghu
 * @discrption： 编辑距离
 * 给定两个字符串 s1、s2，将 s1 编辑成 s2，编辑方式分为 删除（代价dc）、添加（代价ic）、替换（代价rc）。求如何能以最低代价将 s1 编辑为 s2。
 * 应用：可以描述两个字符串的相似程度
 * @create 2022-08-10 9:33
 */
public class P32_EditDistance {

    // 牛客NC35题
    public int minCost(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length + 1;
        int M = str2.length + 1;
        int[][] dp = new int[N][M];
        for (int i = 1; i < N; i++) {
            dp[i][0] = dc * i;
        }
        for (int i = 1; i < M; i++) {
            dp[0][i] = ic * i;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = dp[i - 1][j - 1] + (str1[i-1] == str2[j-1] ? 0 : rc);
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + dc);
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + ic);
            }
        }
        return dp[N-1][M-1];
    }

    // leetcode 的 72题，是该题的简化版，增删改的代价都是1
    public int minDistance(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length + 1;
        int M = str2.length + 1;
        int[][] dp = new int[N][M];
        for (int i = 1; i < N; i++) {
            dp[i][0] = 1 * i;
        }
        for (int i = 1; i < M; i++) {
            dp[0][i] = 1 * i;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = dp[i - 1][j - 1] + (str1[i-1] == str2[j-1] ? 0 : 1);
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + 1);
            }
        }
        return dp[N-1][M-1];
    }

}

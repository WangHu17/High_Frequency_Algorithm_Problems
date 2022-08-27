/**
 * @author wanghu
 * @discrption： 一个字符串至少需要添加多少个字符能整体变成回文串。
 * @create 2022-08-27 10:55
 */
public class P57_ToPalindrome {

    public int minInsertions(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }
}

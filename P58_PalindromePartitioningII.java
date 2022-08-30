/**
 * @author wanghu
 * @discrption： 一个字符串至少要切几刀能让切出来的子串都是回文串
 * @create 2022-08-28 12:04
 */
public class P58_PalindromePartitioningII {

    public int minCut(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 0;
        boolean[][] checkMap = createCheckMap(str);
        for (int i = N - 1; i >= 0; i--) {
            if (checkMap[i][N - 1]) {
                dp[i] = 1;
            } else {
                int next = Integer.MAX_VALUE;
                for (int j = i; j < N; j++) {
                    if (checkMap[i][j]) {
                        next = Math.min(next, dp[j + 1]);
                    }
                }
                dp[i] = 1 + next;
            }
        }
        return dp[0] - 1;
    }

    private boolean[][] createCheckMap(char[] str) {
        int N = str.length;
        boolean[][] res = new boolean[N][N];
        for (int i = 0; i < N - 1; i++) {
            res[i][i] = true;
            res[i][i + 1] = str[i] == str[i + 1];
        }
        res[N - 1][N - 1] = true;
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                res[i][j] = str[i] == str[j] && res[i + 1][j - 1];
            }
        }
        return res;
    }

}

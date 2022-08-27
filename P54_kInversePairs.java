/**
 * @author wanghu
 * @discrption： 给一个 n，一个 k。1~n的所有数字组成的所有排列中，逆序对为k个的有多少个排列。
 * @create 2022-08-26 11:29
 */
public class P54_kInversePairs {

    public int kInversePairs(int n, int k) {
        if (n < 1 || k < 0) return 0;
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        int mod = 1000000007;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % mod;
                if (j >= i){
                    dp[i][j] = (dp[i][j] - dp[i-1][j-i] + mod) % mod;
                }
            }
        }
        return dp[n][k];
    }

}

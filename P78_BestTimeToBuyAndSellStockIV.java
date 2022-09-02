/**
 * @author wanghu
 * @discrption： 手中最多只能持有一支股票，即卖掉手中的股票后才能再买，最多可以买卖 k 次，求最多能赚多少钱。
 * @create 2022-09-02 9:45
 */
public class P78_BestTimeToBuyAndSellStockIV {

    public int maxProfit(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        if (k >= N / 2) {
            return P76_BestTimeToBuyAndSellStockII.maxProfit(arr);
        }
        int[][] dp = new int[N][k + 1];
        for (int j = 1; j <= k; j++) {
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j - 1] - arr[1], dp[0][j - 1] - arr[0]);
            dp[1][j] = Math.max(p1, best + arr[1]);
            for (int i = 2; i < N; i++) {
                p1 = dp[i - 1][j];
                int newp = dp[i][j - 1] - arr[i];
                best = Math.max(best, newp);
                dp[i][j] = Math.max(p1, best + arr[i]);
            }
        }
        return dp[N - 1][k];
    }

}

/**
 * @author wanghu
 * @discrption： 手中最多只能持有一支股票，即卖掉手中的股票后才能再买，最多可以买卖 2 次，求最多能赚多少钱。
 * @create 2022-09-01 10:59
 */
public class P77_BestTimeToBuyAndSellStockIII {

    public int maxProfit(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        int[][] dp = new int[N][3];
        for (int j = 1; j <= 2; j++) {
            int p1 = dp[0][j];
            int best = dp[0][j - 1] - arr[0];
            dp[0][j] = Math.max(p1, best + arr[0]);
            for (int i = 1; i < N; i++) {
                p1 = dp[i - 1][j];
                int newp = dp[i][j - 1] - arr[i];
                best = Math.max(best, newp);
                dp[i][j] = Math.max(p1, best + arr[i]);
            }
        }
        return dp[N - 1][2];
    }

}

/**
 * @author wanghu
 * @discrption： 手中最多只能持有一支股票，即卖掉手中的股票后才能再买，且卖掉后必须隔一个时间点才能再次买，可以买卖任意次，求最多能赚多少钱。
 * @create 2022-09-02 10:52
 */
public class P79_BestTimeToBuyAndSellStockWithCooldown {

    public int maxProfit(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int maxPrice = 0;
        for (int n : arr) maxPrice = Math.max(maxPrice, n);
        int[][][] dp = new int[2][arr.length + 1][maxPrice + 1];
        return process(arr, 0, 0, 0, dp);
    }

    private int process(int[] arr, int buy, int index, int buyPrice, int[][][] dp) {
        if (index >= arr.length) return 0;
        if (dp[buy][index][buyPrice] != 0) {
            return dp[buy][index][buyPrice];
        }
        if (buy == 1) {
            int noSell = process(arr, 1, index + 1, buyPrice, dp);
            int yesSell = arr[index] - buyPrice + process(arr, 0, index + 2, 0, dp);
            dp[buy][index][buyPrice] = Math.max(noSell, yesSell);
        } else {
            int noBuy = process(arr, 0, index + 1, 0, dp);
            int yesBuy = process(arr, 1, index + 1, arr[index], dp);
            dp[buy][index][buyPrice] = Math.max(noBuy, yesBuy);
        }
        return dp[buy][index][buyPrice];
    }

}

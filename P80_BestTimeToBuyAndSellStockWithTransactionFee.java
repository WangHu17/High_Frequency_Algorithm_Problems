/**
 * @author wanghu
 * @discrption： 手中最多只能持有一支股票，即卖掉手中的股票后才能再买，可以买卖任意次，但每次交易都要交手续费，求最多能赚多少钱。
 * @create 2022-09-02 11:23
 */
public class P80_BestTimeToBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] arr, int fee) {
        if (arr == null || arr.length == 0) return 0;
        int bestBuy = -arr[0] - fee;
        int bestSell = 0;
        for (int i = 1; i < arr.length; i++) {
            int curBuy = bestSell - arr[i] - fee;
            int curSell = bestBuy + arr[i];
            bestBuy = Math.max(bestBuy, curBuy);
            bestSell = Math.max(bestSell, curSell);
        }
        return bestSell;
    }

}

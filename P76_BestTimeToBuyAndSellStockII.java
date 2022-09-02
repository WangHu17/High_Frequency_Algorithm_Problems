/**
 * @author wanghu
 * @discrption： 手中最多只能持有一支股票，即卖掉手中的股票后才能再买，可以买卖任意多次，求最多能赚多少钱。
 * @create 2022-09-01 10:51
 */
public class P76_BestTimeToBuyAndSellStockII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            int cur = prices[i] - prices[i - 1];
            sum += Math.max(cur, 0);
        }
        return sum;
    }

}

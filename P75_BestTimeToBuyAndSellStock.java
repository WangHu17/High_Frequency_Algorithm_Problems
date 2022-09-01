/**
 * @author wanghu
 * @discrption： 一个数组，数组元素表示每个时间点的股票价格，如果只能做一次交易（即买入一次，卖出一次），最多能赚多少钱？
 * @create 2022-09-01 10:42
 */
public class P75_BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)return 0;
        int min = prices[0];
        int max = 0;
        for (int price:prices) {
            min = Math.min(min, price);
            max = Math.max(max, price - min);
        }
        return max;
    }

}

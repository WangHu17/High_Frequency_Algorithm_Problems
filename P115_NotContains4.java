/**
 * @author wanghu
 * @discrption： 真实里程
 * 正常的里程表会依次显示自然数表示里程
 * 吉祥的里程表会忽略含有4的数字而跳到下一个完全不含有4的数
 * 正常：1 2 3 4 5 6 7 8 9 10 11 12 13 14 15       X
 * 吉祥：1 2 3 5 6 7 8 9 10 11 12 13 15 16 17 ... 38 39 50 51 52 53 55
 * 给定一个吉祥里程表的数字num(当然这个数字中不含有4)
 * 返回这个数字代表的真实里程。
 * @create 2022-09-11 16:26
 */
public class P115_NotContains4 {

    public long notContains4Nums(long num) {
        if (num < 0) return 0;
        long res = 0;
        for (long base = 1, cur = 0; num != 0; num /= 10, base *= 9) {
            cur = num * 10;
            res += (cur < 4 ? cur : cur - 1) * base;
        }
        return res;
    }

}

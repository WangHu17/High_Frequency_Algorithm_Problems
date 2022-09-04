/**
 * @author wanghu
 * @discrption： 约瑟夫环问题
 * @create 2022-09-04 11:09
 */
public class P85_JosephusProblem {

    public int lastRemaining(int n, int m) {
        int res = 1;
        int r = 1;
        while (r <= n) {
            res = (res + m - 1) % (r++) + 1;
        }
        return res - 1;
    }

}

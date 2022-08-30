/**
 * @author wanghu
 * @discrption： 有放回抽牌
 * 面值为1～N的牌组成一组，每次你从组里等概率的抽出1～N中的一张下次抽会换一个新的组，有无限组。当累加和＜a时，你将一直抽牌，
 * 当累加和 >=a且＜b时，你将获胜，当累加和 >=b 时，你将失败。返回获胜的概率，给定的参数为N，a，b
 * @create 2022-08-30 10:54
 */
public class P65_NCardsABWin {

    public static double f(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0) return 0.0;
        if (b - a >= N) return 1.0;
        return p(0, N, a, b);
    }

    private static double p(int cur, int N, int a, int b) {
        if (cur >= a && cur < b) return 1.0;
        if (cur >= b) return 0.0;
        double w = 0.0;
        for (int i = 1; i <= N; i++) {
            w += p(cur + i, N, a, b);
        }
        return w / N;
    }

}

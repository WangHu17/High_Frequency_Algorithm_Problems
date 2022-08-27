/**
 * @author wanghu
 * @discrption： 给定一个字符串，长度为奇数，字符串是由0、1、&、|、^这些符号组成，数字之间是运算符。编写一个函数 f(String str, int b)，
 * b 为 true 表示字符串最后的运算结果为 true，为 false 表示字符串最后的运算结果为 false。函数返回 给这个字符串添加小括号使得运算结果为 b 的方式有多少种。
 * 例如：0&1|1，变为 (0&1)|1 的结果是 true，而变为 0&(1|1) 的结果是 false。
 * @create 2022-08-27 10:03
 */
public class P56_BooleanEvaluation {

    public int countEval(String express, int desired) {
        if (express == null || express.equals("")) return 0;
        char[] exp = express.toCharArray();
        int N = exp.length;
        Info[][] dp = new Info[N][N];
        Info info = func(exp, 0, exp.length - 1, dp);
        return desired == 1 ? info.t : info.f;
    }

    class Info {
        public int t;
        public int f;

        public Info(int t, int f) {
            this.t = t;
            this.f = f;
        }
    }

    public Info func(char[] exp, int L, int R, Info[][] dp) {
        if (dp[L][R] != null) return dp[L][R];
        int t = 0;
        int f = 0;
        if (L == R) {
            t = exp[L] == '1' ? 1 : 0;
            f = exp[L] == '0' ? 1 : 0;
        } else {
            for (int split = L + 1; split < R; split += 2) {
                Info lInfo = func(exp, L, split - 1, dp);
                Info rInfo = func(exp, split + 1, R, dp);
                int a = lInfo.t;
                int b = lInfo.f;
                int c = rInfo.t;
                int d = rInfo.f;
                switch (exp[split]) {
                    case '&':
                        t += a * c;
                        f += b * c + a * d + b * d;
                        break;
                    case '|':
                        t += a * c + a * d + b * c;
                        f += b * d;
                        break;
                    case '^':
                        t += a * d + b * c;
                        f += a * c + b * d;
                        break;
                }
            }
        }
        dp[L][R] = new Info(t, f);
        return dp[L][R];
    }

}

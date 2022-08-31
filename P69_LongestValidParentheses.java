/**
 * @author wanghu
 * @discrption： 最长有效括号子串长度
 * 给定一个只由左括号和右括号的字符串，返回最长的有效括号子串的长度。
 * @create 2022-08-31 9:42
 */
public class P69_LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < N; i++) {
            if (str[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

}

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author wanghu
 * @discrption： 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？比如 s1＝＂abcde＂， s2 ＝＂axbc＂。
 * @create 2022-08-10 9:59
 */
public class P33_DeleteDistance {

    // 情况一：s1 的长度很大为 N，s2 的长度很小为 M
    public int minCost1(String s1, String s2) {
        ArrayList<String> subs = new ArrayList<>();
        process(s2.toCharArray(), 0, "", subs);
        subs.sort(new Comp());
        for (String str : subs) {
            if (s1.contains(str)) {
                return s2.length() - str.length();
            }
        }
        return s2.length();
    }

    public void process(char[] str, int index, String cur, ArrayList<String> subs) {
        if (index == str.length) {
            subs.add(cur);
        } else {
            process(str, index + 1, cur, subs);
            process(str, index + 1, cur + str[index], subs);
        }
    }

    class Comp implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    }

    // 情况二：s2 的长度较大
    public int minCost2(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = i + 1; j <= s1.length(); j++) {
                ans = Math.min(ans, process(s2, s1.substring(i, j)));
            }
        }
        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }

    public int process(String s1, String s2) {
        if (s1.length() < s2.length()) return Integer.MAX_VALUE;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i <= N; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= Math.min(M, i); j++) {
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (str1[i - 1] == str2[j - 1] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[N][M];
    }

    @Test
    public void test() {

        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 10000;
        boolean pass = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = minCost1(str1, str2);
            int ans2 = minCost2(str1, str2);
            if (ans1 != ans2) {
                pass = false;
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }

    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

}

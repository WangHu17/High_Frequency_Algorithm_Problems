/**
 * @author wanghu
 * @discrption： 判断目标字符串 target 是否可以由字符串 s1 和字符串 s2 交错组成，字符串 s1 和 s2 在 target 中的相对位置不变。
 * @create 2022-08-09 10:01
 */
public class P28_InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str3.length != str1.length + str2.length) return false;
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) break;
            dp[i][0] = true;
        }
        for (int i = 1; i <= str2.length; i++) {
            if (str2[i - 1] != str3[i - 1]) break;
            dp[0][i] = true;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if ((str1[i - 1] == str3[i + j - 1] && dp[i - 1][j]) || (str2[j - 1] == str3[i + j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }

}

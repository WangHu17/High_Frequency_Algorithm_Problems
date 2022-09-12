/**
 * @author wanghu
 * @discrption： 最短包含子串
 * 给定两个字符串 str1 和 str2
 * 在 str1 中寻找一个最短子串，能包含 str2 的所有字符
 * 字符顺序无所谓，str1的这个最短子串也可以包含多余的字符
 * 返回这个最短包含子串。
 * @create 2022-09-12 14:20
 */
public class P118_MinWindowLength {

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int[] count = new int[256];
        for (char c : target) {
            count[c]++;
        }
        int res = Integer.MAX_VALUE;
        int L = 0;
        int R = 0;
        int ansL = -1;
        int ansR = -1;
        int all = t.length();
        while (R < s.length()) {
            count[str[R]]--;
            if (count[str[R]] >= 0) all--;
            if (all == 0) {
                while (count[str[L]] < 0) {
                    count[str[L++]]++;
                }
                if (res > R - L + 1) {
                    res = R - L + 1;
                    ansL = L;
                    ansR = R;
                }
                all++;
                count[str[L++]]++;
            }
            R++;
        }
        return res == Integer.MAX_VALUE ? "" : s.substring(ansL, ansR + 1);
    }

}

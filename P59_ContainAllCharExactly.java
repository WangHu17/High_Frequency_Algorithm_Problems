/**
 * @author wanghu
 * @discrption： 由 m 个指定字符构成的子串
 * 给定长度为m的字符串aim，以及一个长度为 n 的字符串 str，问能否在 str 中找到一个长度为 m 的连续子串，使得这个子串刚好由 aim 的 m 个字符组成，
 * 顺序无所谓，返回任意满足条件的一个子串的起始位置，未找到返回-1。
 * @create 2022-08-29 16:51
 */
public class P59_ContainAllCharExactly {

    public int containExactly(String aim, String s) {
        if (aim == null || s == null || aim.length() == 0 || s.length() == 0 || aim.length() > s.length()) return -1;
        char[] str1 = aim.toCharArray();
        int M = str1.length;
        int[] count = new int[256];
        for (char c : str1) {
            count[c]++;
        }
        int R = 0;
        char[] str2 = s.toCharArray();
        int all = M;
        for (; R < M; R++) {
            if (--count[str2[R]] >= 0) all--;
        }
        for (; R < str2.length; R++) {
            if (all == 0) {
                return R - M;
            }
            if (--count[str2[R]] >= 0) {
                all--;
            }
            if (++count[str2[R - M]] > 0) {
                all++;
            }
        }
        return all == 0 ? R - M : -1;
    }

}

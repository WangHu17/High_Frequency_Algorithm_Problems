import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 最长无重复字符的子串长度
 * @create 2022-08-04 15:20
 */
public class P16_LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[] map = new int[256];
        Arrays.fill(map, -1);
        int pre = 1;
        int res = 1;
        map[str[0]] = 0;
        for (int i = 1; i < N; i++) {
            pre = Math.min(i - map[str[i]], pre + 1);
            res = Math.max(res, pre);
            map[str[i]] = i;
        }
        return res;
    }

}

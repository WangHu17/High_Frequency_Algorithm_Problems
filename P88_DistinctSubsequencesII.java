import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 不同的子序列数量
 * 给定一个字符串Str，返回Str的所有子序列中有多少不同的字面值。
 * @create 2022-09-04 16:43
 */
public class P88_DistinctSubsequencesII {

    // 包含空集
    public int distinctSubseqII(String s) {
        if (s == null || s.length() == 0) return 0;
        int m = 1000000007;
        int newAdd = 0;
        int all = 1;
        int curAll = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        char[] str = s.toCharArray();
        for (char x : str) {
            newAdd = all;
            curAll = (newAdd + all) % m;
            curAll = (curAll - map.getOrDefault(x, 0) + m) % m;
            all = curAll;
            map.put(x, newAdd);
        }
        return all;
    }

}

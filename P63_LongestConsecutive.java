import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 最长连续子序列
 * @create 2022-08-30 10:38
 */
public class P63_LongestConsecutive {

    public static int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
                int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int posLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                int all = preLen + posLen + 1;
                map.put(num - preLen, all);
                map.put(num + posLen, all);
                res = Math.max(res, all);
            }
        }
        return res;
    }

}

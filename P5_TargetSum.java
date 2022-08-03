import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 5、加 +- 构成 target
 * 给定一个数组，在每个数前决定符号（+或-），最终结果要等于 target，求有多少种方法。
 * @create 2022-08-01 10:59
 */
public class P5_TargetSum {

    // leetcode 494题

    // 暴力递归 + 缓存
    public int findTargetSumWays(int[] arr, int target) {
        HashMap<Integer, HashMap<Integer, Integer>> dp = new HashMap<>();
        return process(arr, 0, target, dp);
    }

    public int process(int[] arr, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
            return dp.get(index).get(rest);
        }
        int ans = 0;
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        } else {
            ans = process(arr, index + 1, rest - arr[index], dp) + process(arr, index + 1, rest + arr[index], dp);
        }
        if (!dp.containsKey(index)) {
            dp.put(index, new HashMap<>());
        }
        dp.get(index).put(rest, ans);
        return ans;
    }

}

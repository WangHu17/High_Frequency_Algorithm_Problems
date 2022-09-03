
import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 给定一个正数数组arr，返回arr的子集不能累加出的大于数组最小值的最小正数。
 * @create 2022-09-03 17:40
 */
public class P82_SmallestUnFormedSum {

    // 如果数组中没有1
    public int unformedSum1(int[] arr) {
        if (arr == null || arr.length == 0) return 1;
        int sum = 0;
        int min = arr[0];
        for (int n : arr) {
            sum += n;
            min = Math.min(min, n);
        }
        int N = arr.length;
        boolean[][] dp = new boolean[N][sum+1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j] || (j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : false);
            }
        }
        for (int i = min; i <= sum; i++) {
            if (!dp[N - 1][i]) {
                return i;
            }
        }
        return sum + 1;
    }

    //如果数组中有1
    public int unformedSum2(int[] arr) {
        Arrays.sort(arr);
        int range = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }

}

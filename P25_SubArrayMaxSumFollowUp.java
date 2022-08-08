/**
 * @author wanghu
 * @discrption： 返回一个数组中，选择的数字不能相邻的情况下，最大子序列累加和。（打家劫舍）
 * @create 2022-08-08 14:41
 */
public class P25_SubArrayMaxSumFollowUp {

    public static int rob(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        if (N == 1) return arr[0];
        if (N == 2) return Math.max(arr[0], arr[1]);
        int[] dp = new int[N];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(Math.max(arr[i], dp[i - 1]), arr[i] + dp[i - 2]);
        }
        return dp[N - 1];
    }

}

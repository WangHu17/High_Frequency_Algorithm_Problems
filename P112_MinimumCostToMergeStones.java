/**
 * @author wanghu
 * @discrption： 合并石头的最低成本
 * 有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。
 * 每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。
 * 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
 * @create 2022-09-11 14:00
 */
public class P112_MinimumCostToMergeStones {

    public int mergeStones(int[] stones, int K) {
        int N = stones.length;
        if ((N - 1) % (K - 1) > 0) return -1;
        int[] preSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }
        int[][][] dp = new int[N][N][K+1];
        return process(0, N - 1, 1, stones, K, preSum, dp);
    }

    private int process(int L, int R, int P, int[] arr, int K, int[] preSum, int[][][] dp) {
        if (dp[L][R][P] != 0) {
            return dp[L][R][P];
        }
        if (L == R) {
            return P == 1 ? 0 : -1;
        }
        if (P == 1) {
            int next = process(L, R, K, arr, K, preSum, dp);
            if (next == -1) {
                dp[L][R][P] = -1;
                return -1;
            }else {
                dp[L][R][P] = next + preSum[R + 1] - preSum[L];
                return dp[L][R][P];
            }
        } else {
            int res = Integer.MAX_VALUE;
            for (int mid = L; mid < R; mid += K - 1) {
                int next1 = process(L, mid, 1, arr, K, preSum, dp);
                int next2 = process(mid + 1, R, P-1, arr, K, preSum, dp);
                if (next1 == -1 || next2 == -1){
                    dp[L][R][P] = -1;
                    return -1;
                }else {
                    res = Math.min(res, next1 + next2);
                }
            }
            dp[L][R][P] = res;
            return res;
        }
    }

}

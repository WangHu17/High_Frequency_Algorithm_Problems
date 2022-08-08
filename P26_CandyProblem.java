/**
 * @author wanghu
 * @discrption： 26、分糖果问题
 * 一个数组表示每个孩子的分值，每个孩子至少一颗糖，如果分值比相邻的大，那么分得的糖必须比它多，相等不管。求需要多少块糖。
 * @create 2022-08-08 14:52
 */
public class P26_CandyProblem {

    public int candy(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        int[] left = new int[N];
        int[] right = new int[N];
        for (int i = 1; i < N; i++) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = N - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans + N;
    }

}

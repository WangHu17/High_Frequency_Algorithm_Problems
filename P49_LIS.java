/**
 * @author wanghu
 * @discrption： 最长递增子序列
 * @create 2022-08-25 10:25
 */
public class P49_LIS {

    public int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        int[] ends = new int[N];
        ends[0] = arr[0];
        int right = 0;
        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = (l + r) / 2;
                if (ends[m] < arr[i]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
        }
        return right + 1;
    }

}

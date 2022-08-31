import java.util.TreeSet;

/**
 * @author wanghu
 * @discrption： <= k最大的子数组累加和
 * @create 2022-08-31 10:08
 */
public class P70_MaxSubArraySumLessOrEqualK {

    public int getMaxLessOrEqualK(int[] arr, int K) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (set.ceiling(sum - K) != null) {
                max = Math.max(max, sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }
        return max;
    }

}

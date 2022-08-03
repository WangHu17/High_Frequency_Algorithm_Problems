/**
 * @author wanghu
 * @discrption： 给定一个数组arr，只能对arr中的一个子数组排序，但是想让arr整体都有序，返回满足这一设定的子数组中，最短的是多长。
 * @create 2022-08-03 10:14
 */
public class P9_MinLengthForSort {

    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int N = nums.length;
        int right = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (max > nums[i]) {
                right = i;
            }
            max = Math.max(max, nums[i]);
        }
        int left = N;
        int min = Integer.MAX_VALUE;
        for (int i = N - 1; i >= 0; i--) {
            if (min < nums[i]) {
                left = i;
            }
            min = Math.min(min, nums[i]);
        }
        return Math.max(0, right - left + 1);
    }

}

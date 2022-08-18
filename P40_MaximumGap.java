/**
 * @author wanghu
 * @discrption： 给定一个数组arr，返回如果排序之后，相邻两数的最大差值。
 * 例如：数组 [9, 6, 2, 1]，排序后是 [1, 2, 6, 9]，最大差值是 6 - 2 = 4。
 * @create 2022-08-18 15:32
 */
public class P40_MaximumGap {

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int N = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if (max == min) return 0;
        boolean[] hasNum = new boolean[N + 1];
        int[] maxArr = new int[N + 1];
        int[] minArr = new int[N + 1];
        int bid = 0;
        for (int i = 0; i < N; i++) {
            bid = bucket(nums[i], N, min, max);
            maxArr[bid] = hasNum[bid] ? Math.max(maxArr[bid], nums[i]) : nums[i];
            minArr[bid] = hasNum[bid] ? Math.min(minArr[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxArr[0];
        for (int i = 1; i <= N; i++) {
            if (hasNum[i]){
                res = Math.max(res, minArr[i] - lastMax);
                lastMax = maxArr[i];
            }
        }
        return res;
    }

    public int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

}

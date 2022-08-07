import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 累加和最接近 sum 的子序列与 sum 的差值
 * @create 2022-08-07 10:24
 */
public class P20_ClosestSubsequenceSum {

    public int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0)return 0;
        int N = nums.length;
        int[] l = new int[1 << 20];
        int[] r = new int[1 << 20];
        int le = process(nums, 0, N >> 1, 0, 0, l);
        int re = process(nums, N >> 1, N, 0, 0, r);
        Arrays.sort(l, 0, le);
        Arrays.sort(r, 0, re--);
        int res = Math.abs(goal);
        for (int i=0; i<le; i++) {
            int rest = goal - l[i];
            while (re > 0 && Math.abs(rest - r[re-1]) <= Math.abs(rest - r[re])){
                re--;
            }
            res = Math.min(res, Math.abs(rest - r[re]));
        }
        return res;
    }

    public int process(int[] nums, int index, int end, int sum, int fillIndex, int[] arr) {
        if (index == end){
            arr[fillIndex++] = sum;
        }else {
            fillIndex = process(nums, index+1, end, sum, fillIndex, arr);
            fillIndex = process(nums, index+1, end, sum + nums[index], fillIndex, arr);
        }
        return fillIndex;
    }

}

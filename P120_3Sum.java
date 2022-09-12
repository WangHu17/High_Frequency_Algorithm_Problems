import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wanghu
 * @discrption： 三和问题
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * @create 2022-09-12 15:01
 */
public class P120_3Sum {

    public List<List<Integer>> threeSum(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            if (i == N-1 || nums[i] != nums[i+1]) {
                List<List<Integer>> lists = twoSum(nums, i - 1, -nums[i]);
                for (List<Integer> list:lists) {
                    list.add(nums[i]);
                    res.add(list);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int end, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        int L = 0;
        int R = end;
        while (L < R) {
            if (nums[L] + nums[R] < sum) {
                L++;
            } else if (nums[L] + nums[R] > sum) {
                R--;
            } else {
                if (L == 0 || nums[L - 1] != nums[L]) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[L]);
                    list.add(nums[R]);
                    res.add(list);
                }
                L++;
            }
        }
        return res;
    }

}

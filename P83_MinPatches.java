import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 给定一个已排序的正整数数组 nums ，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，
 * 使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
 * 请返回 满足上述要求的最少需要补充的数字个数 。
 * @create 2022-09-03 19:49
 */
public class P83_MinPatches {

    public int minPatches(int[] arr, int aim) {
        int res = 0;
        long range = 0;
        Arrays.sort(arr);
        for (int i=0; i<arr.length; i++) {
            while (arr[i] > range + 1) {
                range += range + 1;
                res++;
                if (range >= aim){
                    return res;
                }
            }
            range += arr[i];
            if (range >= aim){
                return res;
            }
        }
        while (range < aim) {
            range += range + 1;
            res++;
        }
        return res;
    }

    @Test
    public void test() {
        int[] test = { 1, 2, 31, 33 };
        int n = 2147483647;
        System.out.println(minPatches(test, n));

    }

}

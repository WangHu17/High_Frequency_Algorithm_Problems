import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author wanghu
 * @discrption： 给定一个有正、有负、有0的数组arr，求 arr 的子序列是否有和等于 k 的。
 * @create 2022-09-03 14:11
 */
public class P81_SumOfSubsequence {

    public boolean isSum1(int[] arr, int k) {
        if (k == 0) return true;
        if (arr == null || arr.length == 0) return false;
        HashMap<Integer, HashMap<Integer, Boolean>> dp = new HashMap<>();
        return process(arr, arr.length - 1, k, dp);
    }

    public boolean process(int[] arr, int i, int sum, HashMap<Integer, HashMap<Integer, Boolean>> dp) {
        if (dp.containsKey(i) && dp.get(i).containsKey(sum)) {
            return dp.get(i).get(sum);
        }
        boolean ans = false;
        if (sum == 0) {
            ans = true;
        } else if (i != -1) {
            ans = process(arr, i - 1, sum, dp) || process(arr, i - 1, sum - arr[i], dp);
        }
        if (!dp.containsKey(i)) {
            dp.put(i, new HashMap<>());
        }
        dp.get(i).put(sum, ans);
        return ans;
    }

    // 如果arr中的数值很大， 但是arr的长度不大， 怎么做?
    // 分治
    public boolean isSum2(int[] arr, int k) {
        if (k == 0) return true;
        if (arr == null || arr.length == 0) return false;
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        int mid = arr.length >> 1;
        process2(arr, 0, mid, 0, set1);
        process2(arr, mid, arr.length, 0, set2);
        for (Integer n : set2) {
            if (set1.contains(k - n)) {
                return true;
            }
        }
        return false;
    }

    public void process2(int[] arr, int i, int j, int sum, HashSet<Integer> set) {
        if (i == j) {
            set.add(sum);
        } else {
            process2(arr, i + 1, j, sum, set);
            process2(arr, i + 1, j, sum + arr[i], set);
        }
    }

    // 为了测试
    public int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * ((max << 1) + 1)) - max;
        }
        return arr;
    }

    @Test
    public void test() {
        int N = 20;
        int M = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * (N + 1));
            int[] arr = randomArray(size, M);
            int sum = (int) (Math.random() * ((M << 1) + 1)) - M;
            boolean ans1 = isSum1(arr, sum);
            boolean ans2 = isSum2(arr, sum);
            if (ans1 ^ ans2) {
                System.out.println("出错了！");
                System.out.print("arr : ");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("sum : " + sum);
                System.out.println("方法一答案 : " + ans1);
                System.out.println("方法二答案 : " + ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}

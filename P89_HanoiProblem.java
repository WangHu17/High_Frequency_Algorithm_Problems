import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 汉诺塔最优状态
 * 给定一个数组，代表汉诺塔此刻的状态，数字表示在哪根柱子上，索引越大盘子越大。求此刻的状态是最优解的第几步，如果不是最优解的其中一步返回-1。
 * @create 2022-09-04 17:08
 */
public class P89_HanoiProblem {

    public int kth(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int N = arr.length;
        return process(arr, N - 1, 1, 3, 2);
    }

    public int process(int[] arr, int index, int from, int to, int other) {
        if (index == -1) {
            return 0;
        }
        if (arr[index] == other) {
            return -1;
        }
        if (arr[index] == from) {
            return process(arr, index - 1, from, other, to);
        } else {
            int p1 = (1 << index) - 1;
            int p2 = 1;
            int p3 = process(arr, index - 1, other, to, from);
            if (p3 == -1) {
                return -1;
            }
            return p1 + p2 + p3;
        }
    }

    public int step2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int tmp = 0;
        while (i >= 0) {
            if (arr[i] != from && arr[i] != to) {
                return -1;
            }
            if (arr[i] == to) {
                res += 1 << i;
                tmp = from;
                from = mid;
            } else {
                tmp = to;
                to = mid;
            }
            mid = tmp;
            i--;
        }
        return res;
    }

    @Test
    public void test() {
        int[] arr = { 3, 3, 2, 1 };
        System.out.println(kth(arr));
        System.out.println(step2(arr));
    }

}

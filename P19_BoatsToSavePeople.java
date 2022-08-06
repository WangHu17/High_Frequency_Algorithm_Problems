import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 给定一个正数数组 arr，代表若干人的体重，再给定一个正数 limit，表示所有船共同拥有的载重量，
 * 每艘船最多坐两人，且不能超过载重，想让所有的人同时过河，并且用最好的分配方法让船尽量少，返回最少的船数。
 * @create 2022-08-06 14:56
 */
public class P19_BoatsToSavePeople {

    public int numRescueBoats1(int[] arr, int limit) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        Arrays.sort(arr);
        if (arr[N - 1] > limit) return -1;
        int lessR = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= limit / 2) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) return N;
        int noUsed = 0;
        int L = lessR;
        int R = lessR + 1;
        while (L >= 0) {
            int solved = 0;
            while (R < N && arr[R] + arr[L] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                L = L - solved;
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int rest = N - all - used;
        return used + ((noUsed + 1) >> 1) + rest;
    }

}

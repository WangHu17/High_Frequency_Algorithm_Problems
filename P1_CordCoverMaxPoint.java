import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 1、绳子压点
 * 给定一个有序数组arr，代表坐落在X轴上的点。给定一个正数K，代表绳子的长度。返回绳子最多压中几个点？即使绳子边缘处盖住点也算盖住。
 * @create 2022-08-01 9:28
 */
public class P1_CordCoverMaxPoint {

    // 解法一：二分
    public static int maxPoints1(int[] arr, int k) {
        int max = 1;
        for (int i = 0; i < arr.length; i++) {
            int near = near(arr, i, arr[i] - k);
            max = Math.max(max, i - near + 1);
        }
        return max;
    }

    public static int near(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    // 解法二：滑动窗口
    public static int maxPoints2(int[] arr, int k) {
        int l = 0;
        int r = 0;
        int max = 0;
        while (l < arr.length) {
            while (r < arr.length && arr[r] <= arr[l] + k){
                r++;
            }
            max = Math.max(max, r - (l++));
        }
        return max;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoints1(arr, L);
            int ans2 = maxPoints2(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }

    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

}

/**
 * @author wanghu
 * @discrption： 给定一个非负数组成的数组，长度一定大于1，想知道数组中哪两个数&的结果最大，返回这个最大结果。
 * @create 2022-08-18 11:29
 */
public class P38_MaxAndValue {

    public static int maxAndValue(int[] arr) {
        int N = arr.length;
        int M = N;
        int ans = 0;
        for (int bit = 30; bit >= 0; bit--) {
            int i = 0;
            int temp = M;
            while (i < M) {
                if ((arr[i] & (1 << bit)) == 0) {
                    swap(arr, i, --M);
                }else {
                    i++;
                }
            }
            if (M == 2) {
                return arr[0] & arr[1];
            }
            if (M < 2) {
                M = temp;
            } else {
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // O(N^2)的暴力解
    public static int maxAndValue1(int[] arr) {
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                max = Math.max(max, arr[i] & arr[j]);
            }
        }
        return max;
    }

    public static int[] randomArray(int size, int range) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * range) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int range = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * maxSize) + 2;
            int[] arr = randomArray(size, range);
            int ans1 = maxAndValue1(arr);
            int ans2 = maxAndValue(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }

}

/**
 * @author wanghu
 * @discrption： 左右最大绝对值
 * 给定一个数组arr，长度为N＞1
 * 从中间切一刀，保证左部分和右部分都有数字，一共有N-1种切法
 * 如此多的切法中，每一种都有：
 * 绝对值（左部分最大值-右部分最大值）
 * 返回最大的绝对值是多少
 * @create 2022-09-10 15:44
 */
public class P109_MaxABSBetweenLeftAndRight {

    public static int maxABS(int[] arr) {
        int max = arr[0];
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[N - 1]);
    }

    public static int maxABS2(int[] arr) {
        int[] lArr = new int[arr.length];
        int[] rArr = new int[arr.length];
        lArr[0] = arr[0];
        rArr[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length; i++) {
            lArr[i] = Math.max(lArr[i - 1], arr[i]);
        }
        for (int i = arr.length - 2; i > -1; i--) {
            rArr[i] = Math.max(rArr[i + 1], arr[i]);
        }
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, Math.abs(lArr[i] - rArr[i + 1]));
        }
        return max;
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxABS(arr));
        System.out.println(maxABS2(arr));
    }

}

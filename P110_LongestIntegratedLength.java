import java.util.Arrays;
import java.util.HashSet;

/**
 * @author wanghu
 * @discrption： 最长可整合子数组长度
 * 定义什么是可整合数组：即排完序后是连续的
 * 一个数组排完序之后，除了最左侧的数外，有arr[i] = arr[i-1]+1，则称这个数组为可整合数组。
 * 比如[5,1,2,4,3]、[6,2,3,1,5,4]都是可整合数组，返回arr中最长可整合子数组的长度。
 * @create 2022-09-10 15:49
 */
public class P110_LongestIntegratedLength {

    public static int maxLen(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        HashSet<Integer> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < N; i++) {
            set.clear();
            int min = arr[i];
            int max = arr[i];
            set.add(arr[i]);
            for (int j = i + 1; j < N; j++) {
                if (set.contains(arr[j])) break;
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                if (max - min == j - i) {
                    res = Math.max(res, j - i + 1);
                }
                set.add(arr[j]);
            }
        }
        return res;
    }

    // 暴力
    public static int getLIL1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;
        // O(N^3 * log N)
        for (int start = 0; start < arr.length; start++) { // 子数组所有可能的开头
            for (int end = start; end < arr.length; end++) { // 开头为start的情况下，所有可能的结尾
                // arr[s ... e]
                // O(N * logN)
                if (isIntegrated(arr, start, end)) {
                    len = Math.max(len, end - start + 1);
                }
            }
        }
        return len;
    }

    public static boolean isIntegrated(int[] arr, int left, int right) {
        int[] newArr = Arrays.copyOfRange(arr, left, right + 1); // O(N)
        Arrays.sort(newArr); // O(N*logN)
        for (int i = 1; i < newArr.length; i++) {
            if (newArr[i - 1] != newArr[i] - 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 3 };
        System.out.println(getLIL1(arr));
        System.out.println(maxLen(arr));
    }

}

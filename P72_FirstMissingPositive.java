/**
 * @author wanghu
 * @discrption： first-missing-positive
 * 找到无序数组中第一个缺失的正整数，要求时间复杂度O(N)，空间复杂度O(1)
 * @create 2022-08-31 11:13
 */
public class P72_FirstMissingPositive {

    public int firstMissingPositive(int[] arr) {
        int L = 0;
        int R = arr.length;
        while (L != R) {
            if (arr[L] == L+1) {
                L++;
            }else if (arr[L] <= L || arr[L] > R || arr[arr[L] - 1] == arr[L]) {
                swap(arr, L, --R);
            }else {
                swap(arr, L, arr[L] - 1);
            }
        }
        return L + 1;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

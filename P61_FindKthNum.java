/**
 * @author wanghu
 * @discrption： 不等长有序的两个数组排完序后第k小的数
 * @create 2022-08-30 9:26
 */
public class P61_FindKthNum {

    public static int findKthNum(int[] arr1, int[] arr2, int k) {
        int[] longs = arr1.length > arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length > arr2.length ? arr2 : arr1;
        int s = shorts.length;
        int l = longs.length;
        if (k <= s) {
            return P60_GetUpMedian.getUpMedian(shorts, 0, k - 1, longs, 0, k - 1);
        }
        if (k > l) {
            if (shorts[k - l - 1] >= longs[l - 1]) {
                return shorts[k - l - 1];
            }
            if (longs[k - s - 1] >= shorts[s - 1]) {
                return longs[k - s - 1];
            }
            return P60_GetUpMedian.getUpMedian(shorts, k - l, s - 1, longs, k - s, l - 1);
        }
        if (longs[k - s - 1] >= shorts[s - 1]) {
            return longs[k - s - 1];
        }
        return P60_GetUpMedian.getUpMedian(shorts, 0, s - 1, longs, k - s, k - 1);
    }

}

/**
 * @author wanghu
 * @discrption： 子数组最大累加和
 * @create 2022-08-08 10:54
 */
public class P23_MaxSubArray {

    public int maxSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int pre = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            pre = Math.max(arr[i], arr[i] + pre);
            max = Math.max(max, pre);
        }
        return max;
    }

}

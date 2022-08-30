/**
 * @author wanghu
 * @discrption： 等长有序的两个数组排完序后的上中位数
 * @create 2022-08-29 20:04
 */
public class P60_GetUpMedian {

    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            if (A[mid1] == B[mid2]){
                return A[mid1];
            }
            if (((e1 - s1 + 1) & 1) == 1) {
                if (A[mid1] > B[mid2]){
                    if (B[mid2] >= A[mid1-1]){
                        return B[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                }else {
                    if (A[mid1] >= B[mid2-1]){
                        return A[mid1];
                    }
                    s1 = mid1 + 1;
                    e2 = mid2 - 1;
                }
            }else {
                if (A[mid1] > B[mid2]){
                    e1 = mid1;
                    s2 = mid2 + 1;
                }else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            }
        }
        return Math.min(A[s1], B[s2]);
    }

}

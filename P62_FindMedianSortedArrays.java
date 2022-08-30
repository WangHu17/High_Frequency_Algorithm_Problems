/**
 * @author wanghu
 * @discrption： leetcode 不等长有序的两个数组的中位数
 * @create 2022-08-30 9:55
 */
public class P62_FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int size = len1 + len2;
        boolean even = (size & 1) == 0;
        if (len1 != 0 && len2 != 0) {
            if (even) {
                return (double) (P61_FindKthNum.findKthNum(nums1, nums2, size / 2) + P61_FindKthNum.findKthNum(nums1, nums2, size / 2 + 1)) / 2;
            } else {
                return P61_FindKthNum.findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (len1 != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        } else if (len2 != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

}

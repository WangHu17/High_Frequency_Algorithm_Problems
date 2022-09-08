/**
 * @author wanghu
 * @discrption： 接雨水问题
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @create 2022-09-08 11:08
 */
public class P105_TrappingRainWater {

    public int trap(int[] arr) {
        if (arr == null || arr.length < 2)return 0;
        int N = arr.length;
        int leftMax = arr[0];
        int rightMax = arr[N-1];
        int L = 1;
        int R = N-2;
        int sum = 0;
        while (L <= R) {
            if (leftMax <= rightMax) {
                sum += Math.max(0, leftMax - arr[L]);
                leftMax = Math.max(leftMax, arr[L++]);
            }else {
                sum += Math.max(0, rightMax - arr[R]);
                rightMax = Math.max(rightMax, arr[R--]);
            }
        }
        return sum;
    }

}

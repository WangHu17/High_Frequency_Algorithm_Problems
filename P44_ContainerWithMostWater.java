/**
 * @author wanghu
 * @discrption： 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * @create 2022-08-22 10:58
 */
public class P44_ContainerWithMostWater {

    public int maxArea(int[] h) {
        int res = 0;
        int l = 0;
        int r = h.length-1;
        while (l < r) {
            res = Math.max(res, Math.min(h[l], h[r]) * (r-l));
            if (h[l] <= h[r]) {
                l++;
            }else {
                r--;
            }
        }
        return res;
    }

}

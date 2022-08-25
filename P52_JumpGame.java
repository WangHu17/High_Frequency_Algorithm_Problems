/**
 * @author wanghu
 * @discrption： 跳跃游戏
 * 给定一个数组，每个数字代表最多可以跳几步，求跳到最后一个位置的最少跳跃次数。
 * @create 2022-08-25 11:09
 */
public class P52_JumpGame {

    public int jump(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int step = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }

}

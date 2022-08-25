/**
 * @author wanghu
 * @discrption： 环形点灯
 * 如果N栈灯排成一个圈，请问最少按下多少次开关，能让灯都亮起来？
 * 0 号灯的开关能影响 N-1、0 和 1 位置的灯；N-1 号灯的开关能影响 N-2、N-1 和 0 位置的灯。
 * @create 2022-08-24 10:31
 */
public class P47_LoopLight {

    public static int loopMinStep(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        if (N == 1) return arr[0] ^ 1;
        if (N == 2) return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        if (N == 3) return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        // 0不变，1不变
        int p1 = traceLoop(arr, arr[1], arr[2], arr[N - 1], arr[0]);
        // 0变，1不变
        int p2 = traceLoop(arr, arr[1] ^ 1, arr[2], arr[N - 1] ^ 1, arr[0] ^ 1);
        // 0不变，1变
        int p3 = traceLoop(arr, arr[1] ^ 1, arr[2] ^ 1, arr[N - 1], arr[0] ^ 1);
        // 0变，1变
        int p4 = traceLoop(arr, arr[1], arr[2] ^ 1, arr[N - 1] ^ 1, arr[0]);
        if (p2 != Integer.MAX_VALUE) p2++;
        if (p3 != Integer.MAX_VALUE) p3++;
        if (p4 != Integer.MAX_VALUE) p4+=2;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    public static int traceLoop(int[] arr, int preStatus, int curStatus, int endStatus, int firstStatus) {
        int i = 3;
        int op = 0;
        while (i < arr.length - 1) {
            if (preStatus == 0) {
                op++;
                preStatus = curStatus ^ 1;
                curStatus = arr[i++] ^ 1;
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        if (preStatus == 0) {
            op++;
            preStatus = curStatus ^ 1;
            endStatus ^= 1;
            curStatus = endStatus;
        } else {
            preStatus = curStatus;
            curStatus = endStatus;
        }
        return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (op + (endStatus ^ 1));
    }

}

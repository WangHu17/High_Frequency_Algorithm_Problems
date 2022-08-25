/**
 * @author wanghu
 * @discrption： 线性点灯
 * 给定一个数组 arr，长度为N，arr 中的值不是0就是1。arr[i] 表示第 i 栈灯的状态，0代表灭灯，1代表亮灯。
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变 i-1、i、i+1 栈灯的状态（灭的变亮、亮的变灭）
 * 问题一：如果N栈灯排成一条直线，请问最少按下多少次开关，可以把所有灯点亮?
 * 0 号灯的开关只能影响 0 和 1 位置的灯；N-1 号灯的开关只能影响 N-2 和 N-1 位置的灯。
 * @create 2022-08-24 9:36
 */
public class P47_NoLoopLight {

    // 递归版本
    public static int noLoopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 不点第0盏灯
        int p1 = process(arr, 2, arr[0], arr[1]);
        // 点第0盏灯
        int p2 = process(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    public static int process(int[] arr, int next, int preStatus, int curStatus) {
        if (next == arr.length) {
            return preStatus != curStatus ? Integer.MAX_VALUE : (preStatus ^ 1);
        }
        if (preStatus == 0) {
            int p = process(arr, next + 1, curStatus ^ 1, arr[next] ^ 1);
            return p == Integer.MAX_VALUE ? p : (p + 1);
        } else {
            return process(arr, next + 1, curStatus, arr[next]);
        }
    }

    // 迭代版本
    public static int noLoopMinStep2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 不点第0盏灯
        int p1 = traceNoLoop(arr, arr[0], arr[1]);
        // 点第0盏灯
        int p2 = traceNoLoop(arr, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    public static int traceNoLoop(int[] arr, int preStatus, int curStatus) {
        int i = 2;
        int res = 0;
        while (i < arr.length) {
            if (preStatus == 0) {
                res++;
                preStatus = curStatus ^ 1;
                curStatus = arr[i++] ^ 1;
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        return preStatus != curStatus ? Integer.MAX_VALUE : (res + (preStatus ^ 1));
    }

    // 生成长度为len的随机数组，值只有0和1两种值
    public static int[] randomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int testTime = 20000;
        int lenMax = 12;
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = noLoopMinStep1(arr);
            int ans2 = noLoopMinStep2(arr);
            if (ans1 != ans2) {
                System.out.println("1 Oops!");
            }
        }
        System.out.println("test end");
    }

}

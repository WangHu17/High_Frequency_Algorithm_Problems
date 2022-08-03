import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 7、打死所有怪兽
 * 给定两个非负数组 x 和 hp，长度都是N，再给定一个正数 range。x有序，x[i] 表示 i 号怪兽在 x 轴上的位置；hp[i] 表示 i 号怪兽的血量，
 * range 表示法师如果站在x位置，用AOE技能打到的范围是 [x-range, x+range]，被打到的每只怪兽损失1点血量，返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
 * @create 2022-08-02 9:45
 */
public class P7_AOE {

    // 贪心
    public static int minAoe1(int[] x, int[] hp, int range) {
        int N = x.length;
        int[] cover = new int[N];
        int r = 0;
        for (int i=0; i<N; i++) {
            while (r < N && x[r] - x[i] < range) {
                r++;
            }
            cover[i] = r;
        }
        int res = 0;
        for (int i=0; i<N; i++) {
            if (hp[i] > 0) {
                int minus = hp[i];
                for (int index = i; index <= cover[i]; index++){
                    hp[index] -= minus;
                }
                res += minus;
            }
        }
        return res;
    }

    // 贪心 + 线段树
    public static int minAoe2(int[] x, int[] hp, int range) {
        return 0;
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 50;
        int X = 500;
        int H = 60;
        int R = 10;
        int testTime = 50000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] x2 = randomArray(len, X);
            Arrays.sort(x2);
            int[] hp2 = randomArray(len, H);
            int[] x3 = copyArray(x2);
            int[] hp3 = copyArray(hp2);
            int range = (int) (Math.random() * R) + 1;
            int ans1 = minAoe1(x2, hp2, range);
            int ans2 = minAoe2(x3, hp3, range);
            if (ans1 != ans2) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

    // 为了测试
    public static int[] randomArray(int n, int valueMax) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * valueMax) + 1;
        }
        return ans;
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int N = arr.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

}

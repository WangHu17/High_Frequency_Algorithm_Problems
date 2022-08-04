/**
 * @author wanghu
 * @discrption： 现有司机N*2人，调度中心会将所有司机平分给A、B两个区域，第i个司机去A可得收入为income[i][0]，第i个司机去B可得收入为income[i][1]，
 * 返回所有调度方案中能使所有司机总收入最高的方案是多少钱。
 * @create 2022-08-04 10:41
 */
public class P13_Drivers {

    // 暴力递归
    public static int maxMoney1(int[][] m) {
        if (m == null || m.length == 0 || (m.length & 1) != 0) {
            return 0;
        }
        int N = m.length;
        return process(m, 0, N >> 1);
    }

    public static int process(int[][] m, int index, int rest) {
        if (index == m.length) {
            return 0;
        }
        if (rest == m.length - index) {
            return m[index][0] + process(m, index + 1, rest - 1);
        }
        if (rest == 0) {
            return m[index][1] + process(m, index + 1, rest);
        }
        int p1 = m[index][0] + process(m, index + 1, rest - 1);
        int p2 = m[index][1] + process(m, index + 1, rest);
        return Math.max(p1, p2);
    }

    // 动态规划
    public static int maxMoney2(int[][] m) {
        if (m == null || m.length == 0 || (m.length & 1) != 0) {
            return 0;
        }
        int N = m.length;
        int M = N >> 1;
        int[][] dp = new int[N+1][M+1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= M; j++) {
                if (j == N - i) {
                    dp[i][j] = m[i][0] + dp[i + 1][j - 1];
                }
                else if (j == 0) {
                    dp[i][j] = m[i][1] + dp[i + 1][j];
                }else {
                    int p1 = m[i][0] + dp[i + 1][j - 1];
                    int p2 = m[i][1] + dp[i + 1][j];
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][M];
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
//            int ans3 = maxMoney3(matrix);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
//                System.out.println(ans3);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

}

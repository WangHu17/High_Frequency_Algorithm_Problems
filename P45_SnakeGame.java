import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 贪吃蛇问题
 * 给定一个矩阵 matrix，值有正、负、0
 * 蛇可以空降到最左列的任何一个位置，初始增长值是0，蛇每一步可以选择右上、右、右下三个方向的任何一个前进
 * 沿途的数字累加起来，作为增长值；但是蛇一旦增长值为负数，就会死去
 * 蛇有一种能力，可以使用一次：把某个格子里的数变成相反数，蛇可以走到任何格子的时候停止，返回蛇能获得的最大增长值。
 * @create 2022-08-23 9:45
 */
public class P45_SnakeGame {

    // 暴力递归
    public int walk1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Info info = f(matrix, i, j);
                res = Math.max(res, Math.max(info.no, info.yes));
            }
        }
        return res;
    }

    class Info {
        public int no;
        public int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    public Info f(int[][] matrix, int i, int j) {
        if (j == 0) {
            int no = Math.max(-1, matrix[i][j]);
            int yes = Math.max(-1, -matrix[i][j]);
            return new Info(no, yes);
        }
        int preNo = -1;
        int preYes = -1;
        Info pre = f(matrix, i, j - 1);
        preNo = Math.max(pre.no, preNo);
        preYes = Math.max(pre.yes, preYes);
        if (i > 0) {
            pre = f(matrix, i - 1, j - 1);
            preNo = Math.max(pre.no, preNo);
            preYes = Math.max(pre.yes, preYes);
        }
        if (i < matrix.length - 1) {
            pre = f(matrix, i + 1, j - 1);
            preNo = Math.max(pre.no, preNo);
            preYes = Math.max(pre.yes, preYes);
        }
        int no = preNo == -1 ? -1 : Math.max(-1, preNo + matrix[i][j]);
        int p1 = preYes == -1 ? -1 : Math.max(-1, preYes + matrix[i][j]);
        int p2 = preNo == -1 ? -1 : Math.max(-1, preNo - matrix[i][j]);
        int yes = Math.max(p1, p2);
        return new Info(no, yes);
    }

    // 动态规划
    public int walk2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][][] dp = new int[row][col][2];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            dp[i][0][0] = matrix[i][0];
            dp[i][0][1] = -matrix[i][0];
            max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
        }
        for (int j = 1; j < col; j++) {
            for (int i = 0; i < row; i++) {
                int preNo = dp[i][j - 1][0];
                int preYes = dp[i][j - 1][1];
                if (i > 0) {
                    preNo = Math.max(preNo, dp[i - 1][j - 1][0]);
                    preYes = Math.max(preYes, dp[i - 1][j - 1][1]);
                }
                if (i < row - 1) {
                    preNo = Math.max(preNo, dp[i + 1][j - 1][0]);
                    preYes = Math.max(preYes, dp[i + 1][j - 1][1]);
                }
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
                if (preNo >= 0) {
                    dp[i][j][0] = matrix[i][j] + preNo;
                    dp[i][j][1] = -matrix[i][j] + preNo;
                }
                if (preYes >= 0) {
                    dp[i][j][1] = Math.max(dp[i][j][1], preYes + matrix[i][j]);
                }
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }
        return max;
    }

    public int[][] generateRandomArray(int row, int col, int value) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
            }
        }
        return arr;
    }

    @Test
    public void test() {
        int N = 7;
        int M = 7;
        int V = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int r = (int) (Math.random() * (N + 1));
            int c = (int) (Math.random() * (M + 1));
            int[][] matrix = generateRandomArray(r, c, V);
            int ans1 = walk1(matrix);
            int ans2 = walk2(matrix);
            if (ans1 != ans2) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                break;
            }
        }
        System.out.println("finish");
    }

}

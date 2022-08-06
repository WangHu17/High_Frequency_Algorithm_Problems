import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 给定一个只有0和1组成的二维数组，返回边框全是1的最大正方形面积。
 * @create 2022-08-06 11:04
 */
public class P18_Largest1BorderedSquare {

    public int largest1BorderedSquare(int[][] m) {
        int r = m.length;
        int c = m[0].length;
        int[][] right = new int[r][c];
        int[][] down = new int[r][c];
        setBorderMap(m, right, down);
        for (int i = Math.min(r, c); i > 0; i--) {
            if (hasSizeSquare(i, right, down)) {
                return i * i;
            }
        }
        return 0;
    }

    public void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i >= 0; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i >= 0; i--) {
            if (m[r - 1][i] == 1) {
                down[r - 1][i] = 1;
                right[r - 1][i] = right[r - 1][i + 1] + 1;
            }
        }
        for (int i = r - 2; i >= 0; i--) {
            for (int j = c - 2; j >= 0; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    public boolean hasSizeSquare(int size, int[][] right, int[][] down) {
        int r = right.length;
        int c = right[0].length;
        for (int i = 0; i <= r - size; i++) {
            for (int j = 0; j <= c - size; j++) {
                if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[][] m = {{1, 1, 1},
                     {1, 0, 1},
                     {1, 1, 1}};
        largest1BorderedSquare(m);

    }

}

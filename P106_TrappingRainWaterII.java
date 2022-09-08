import java.util.PriorityQueue;

/**
 * @author wanghu
 * @discrption： 二维接雨水问题
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * @create 2022-09-08 14:02
 */
public class P106_TrappingRainWaterII {

    class Node {
        public int value;
        public int row;
        public int col;

        public Node(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        int N = heightMap.length;
        int M = heightMap[0].length;
        boolean[][] isEntered = new boolean[N][M];
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.value - b.value);
        for (int i = 0; i < M; i++) {
            isEntered[0][i] = true;
            heap.add(new Node(heightMap[0][i], 0, i));
        }
        for (int i = 1; i < N; i++) {
            isEntered[i][M - 1] = true;
            heap.add(new Node(heightMap[i][M - 1], i, M - 1));
        }
        for (int i = 0; i < M - 1; i++) {
            isEntered[N - 1][i] = true;
            heap.add(new Node(heightMap[N - 1][i], N - 1, i));
        }
        for (int i = 1; i < N - 1; i++) {
            isEntered[i][0] = true;
            heap.add(new Node(heightMap[i][0], i, 0));
        }
        int res = 0;
        int max = 0;
        while (!heap.isEmpty()) {
            Node node = heap.poll();
            max = Math.max(max, node.value);
            int i = node.row;
            int j = node.col;
            if (i > 0 && !isEntered[i - 1][j]) {
                res += Math.max(0, max - heightMap[i - 1][j]);
                isEntered[i - 1][j] = true;
                heap.add(new Node(heightMap[i - 1][j], i - 1, j));
            }
            if (i < N - 1 && !isEntered[i + 1][j]) {
                res += Math.max(0, max - heightMap[i + 1][j]);
                isEntered[i + 1][j] = true;
                heap.add(new Node(heightMap[i + 1][j], i + 1, j));
            }
            if (j > 0 && !isEntered[i][j - 1]) {
                res += Math.max(0, max - heightMap[i][j - 1]);
                isEntered[i][j - 1] = true;
                heap.add(new Node(heightMap[i][j - 1], i, j - 1));
            }
            if (j < M - 1 && !isEntered[i][j + 1]) {
                res += Math.max(0, max - heightMap[i][j + 1]);
                isEntered[i][j + 1] = true;
                heap.add(new Node(heightMap[i][j + 1], i, j + 1));
            }
        }
        return res;
    }

}

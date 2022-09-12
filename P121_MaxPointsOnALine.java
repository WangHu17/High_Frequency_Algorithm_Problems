import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 一条线上的最大点数
 * 给定一个点数组，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，返回位于同一直线上的最大点数。
 * @create 2022-09-12 15:17
 */
public class P121_MaxPointsOnALine {

    public int maxPoints(int[][] points) {
        if (points == null) return 0;
        if (points.length <= 2) return points.length;
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int samePosition = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if (x == 0 && y == 0) {
                    samePosition++;
                } else if (x == 0) {
                    sameX++;
                } else if (y == 0) {
                    sameY++;
                } else {
                    int gcd = gcd(x, y);
                    x /= gcd;
                    y /= gcd;
                    if (!map.containsKey(x)) {
                        map.put(x, new HashMap<>());
                    }
                    if (!map.get(x).containsKey(y)) {
                        map.get(x).put(y, 0);
                    }
                    map.get(x).put(y, map.get(x).get(y) + 1);
                    line = Math.max(line, map.get(x).get(y));
                }
            }
            res = Math.max(res, Math.max(line, Math.max(sameX, sameY)) + samePosition);
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}

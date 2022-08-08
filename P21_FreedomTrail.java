import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 类似于一个老式的电话拨号盘，拨号盘的按钮由字符串 r 表示，目标拨号字符串为 k，保证 r 包含 k 的所有字符。
 * r 围成一圈首尾相连，拨号时将对应的字符旋转到 12:00 方向并按下确认为完成一个拨号（步数为旋转的字符数+一次确认），
 * 拨完一个号后拨号盘保持现状不还原。拨号盘可以顺时针也可以逆时针旋转，求如何拨号能以最少的步数完成。
 * @create 2022-08-08 9:52
 */
public class P21_FreedomTrail {

    public int findRotateSteps(String r, String k) {
        char[] ring = r.toCharArray();
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < ring.length; i++) {
            if (!map.containsKey(ring[i])) {
                map.put(ring[i], new ArrayList<>());
            }
            map.get(ring[i]).add(i);
        }
        char[] str = k.toCharArray();
        int N = ring.length;
        int M = k.length();
        int[][] dp = new int[N][M + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(0, 0, str, map, N, dp);
    }

    public int process(int preButton, int index, char[] str, HashMap<Character, ArrayList<Integer>> map, int N, int[][] dp) {
        if (dp[preButton][index] != -1) {
            return dp[preButton][index];
        }
        int ans = Integer.MAX_VALUE;
        if (index == str.length) {
            ans = 0;
        } else {
            ArrayList<Integer> list = map.get(str[index]);
            for (Integer i : list) {
                int cost = dail(preButton, i, N) + 1 + process(i, index+1, str, map, N, dp);
                ans = Math.min(ans, cost);
            }
        }
        dp[preButton][index] = ans;
        return ans;
    }

    public int dail(int i, int j, int N) {
        return Math.min(Math.abs(i - j), Math.min(i, j) + N - Math.max(i, j));
    }

}

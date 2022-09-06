import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 先序 + 中序 生成 后序
 * 如果只给定一个二叉树先序遍历数组 pre 和中序遍历数组 in，能否不重建树，而直接生成这个二叉树的后序数组，已知二叉树中没有重复值。
 * @create 2022-09-06 11:28
 */
public class P99_PreAndInArrayToPosArray {

    public int[] preInToPos(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        int N = pre.length;
        for (int i = 0; i < N; i++) {
            map.put(in[i], i);
        }
        int[] pos = new int[N];
        process(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1, map);
        return pos;
    }

    public void process(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3, HashMap<Integer, Integer> map) {
        if (L1 > R1) return;
        if (L1 == R1) {
            pos[L3] = pre[L1];
        } else {
            pos[R3] = pre[L1];
            int index = map.get(pre[L1]);
            process(pre, L1 + 1, L1 + index - L2, in, L2, index - 1, pos, L3, L3 + index - L2 - 1, map);
            process(pre, L1 + index - L2 + 1, R1, in, index + 1, R2, pos, L3 + index - L2, R3 - 1, map);
        }
    }

}

import java.util.Arrays;


/**
 * @author wanghu
 * @discrption： 给定一个搜索二叉树先序遍历的数组，无重复值，生成对应的搜索二叉树并返回头节点。
 * @create 2022-08-09 10:59
 */
public class P30_ConstructBinarySearchTreeFromPreorderTraversal {

    public TreeNode bstFromPreorder(int[] pre) {
        if (pre == null || pre.length == 0) return null;
        return process(pre, 0, pre.length - 1);
    }

    public TreeNode process(int[] pre, int L, int R) {
        if (L > R) return null;
        int firstBig = L + 1;
        for (; firstBig <= R; firstBig++) {
            if (pre[firstBig] > pre[L]) break;
        }
        TreeNode head = new TreeNode(pre[L]);
        head.left = process(pre, L + 1, firstBig - 1);
        head.right = process(pre, firstBig, R);
        return head;
    }

    // 单调栈优化
    public TreeNode bstFromPreorder1(int[] pre) {
        if (pre == null || pre.length == 0) return null;
        int N = pre.length;
        int[] nearBig = new int[N];
        Arrays.fill(nearBig, -1);
        int[] stack = new int[N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            while (size > 0 && pre[stack[size - 1]] < pre[i]) {
                nearBig[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        return process1(pre, 0, N-1, nearBig);
    }

    public TreeNode process1(int[] pre, int L, int R, int[] nearBig) {
        if (L > R) return null;
        int firstBig = nearBig[L] == -1 || nearBig[L] > R ? (R + 1) : nearBig[L];
        TreeNode head = new TreeNode(pre[L]);
        head.left = process1(pre, L+1, firstBig-1, nearBig);
        head.right = process1(pre, firstBig, R, nearBig);
        return head;
    }

}

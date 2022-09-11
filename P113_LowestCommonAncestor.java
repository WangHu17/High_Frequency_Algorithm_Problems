import java.util.HashSet;

/**
 * @author wanghu
 * @discrption： 批量查询最低公共祖先
 * 给定数组tree大小为N，表示一共有N个节点
 * tree[i] = j 表示点i的父亲是点 j，tree一定是一棵树而不是森林
 * queries是二维数组，大小为M*2，每一个长度为2的数组都表示一条查询
 * [4,9], 表示想查询4和9之间的最低公共祖先
 * [3,7], 表示想查询3和7之间的最低公共祖先
 * tree和queries里面的所有值，都一定在0~N-1之间
 * 返回一个数组ans，大小为M，ans[i]表示第i条查询的答案
 * @create 2022-09-11 15:57
 */
public class P113_LowestCommonAncestor {

    // 暴力方法
    public int[] query(int[] father, int[][] queries) {
        int N = queries.length;
        int[] res = new int[N];
        HashSet<Integer> set = new HashSet<>();
        for (int i=0; i<N; i++) {
            int node = queries[i][0];
            while (father[node] != node) {
                set.add(node);
                node = father[node];
            }
            set.add(node);
            node = queries[i][1];
            while (!set.contains(node)) {
                node = father[node];
            }
            res[i] = node;
        }
        return res;
    }

}

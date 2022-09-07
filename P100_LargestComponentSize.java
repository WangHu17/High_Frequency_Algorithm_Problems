import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 给定一个由不同正整数组成的非空数组 nums ，考虑下面的图：
 * 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
 * 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
 * 返回 图中最大连通组件的大小 。
 * @create 2022-09-07 10:03
 */
public class P100_LargestComponentSize {

    public int largestComponentSize(int[] arr) {
        int N = arr.length;
        UnionFind unionFind = new UnionFind(N);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            int min = (int) Math.sqrt(num);
            for (int j = 1; j <= min; j++) {
                if (num % j == 0) {
                    if (j != 1) {
                        if (!map.containsKey(j)) {
                            map.put(j, i);
                        } else {
                            unionFind.union(map.get(j), i);
                        }
                    }
                    int other = num / j;
                    if (other != 1) {
                        if (!map.containsKey(other)) {
                            map.put(other, i);
                        } else {
                            unionFind.union(map.get(other), i);
                        }
                    }
                }
            }
        }
        return unionFind.maxSize();
    }

    class UnionFind {
        private int[] parents;
        private int[] sizes;
        private int[] help;

        public UnionFind(int N) {
            parents = new int[N];
            sizes = new int[N];
            help = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public int maxSize() {
            int max = 0;
            for (int size : sizes) {
                max = Math.max(size, max);
            }
            return max;
        }

        public int find(int i) {
            int hi = 0;
            while (i != parents[i]) {
                help[hi++] = i;
                i = parents[i];
            }
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                int max = sizes[f1] > sizes[f2] ? f1 : f2;
                int min = sizes[f1] > sizes[f2] ? f2 : f1;
                parents[min] = max;
                sizes[max] += sizes[min];
            }
        }
    }

}

/**
 * @author wanghu
 * @discrption： 给定一个数组 nums，以及一个二维数组 queries，假设 queries = [[x, m], [x1, m1]]，即挨个求 x 与 nums 中 <= m 的数的最大异或和
 * @create 2022-08-11 11:33
 */
public class P35_MaximumXorWithAnElementFromArray {

    class Node {
        public int min;
        public Node[] nexts;

        public Node() {
            this.min = Integer.MAX_VALUE;
            this.nexts = new Node[2];
        }
    }

    class NumTrie {
        private Node head = new Node();

        public void add(int num) {
            Node cur = head;
            head.min = Math.min(head.min, num);
            for (int i = 30; i >= 0; i--) {
                int path = (num >> i) & 1;
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
                cur.min = Math.min(cur.min, num);
            }
        }

        public int maxXor(int num, int m) {
            if (head.min > m)return -1;
            Node cur = head;
            int ans = 0;
            for (int i = 30; i >= 0; i--) {
                int path = (num >> i) & 1;
                int best = path ^ 1;
                best = (cur.nexts[best] == null || cur.nexts[best].min > m) ? best ^ 1 : best;
                ans |= (path ^ best) << i;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        NumTrie numTrie = new NumTrie();
        for (int num : nums) {
            numTrie.add(num);
        }
        int N = queries.length;
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = numTrie.maxXor(queries[i][0], queries[i][1]);
        }
        return res;
    }

}

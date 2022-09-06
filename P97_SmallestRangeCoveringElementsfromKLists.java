import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author wanghu
 * @discrption： 最小区间
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 * @create 2022-09-06 10:30
 */
public class P97_SmallestRangeCoveringElementsfromKLists {

    class Node {
        public int value;
        public int arrId;
        public int index;

        public Node(int value, int arrId, int index) {
            this.value = value;
            this.arrId = arrId;
            this.index = index;
        }
    }

    class MyComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value != o2.value ? o1.value - o2.value : o1.arrId - o2.arrId;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        int N = nums.size();
        TreeSet<Node> treeSet = new TreeSet<>(new MyComparator());
        for (int i = 0; i < N; i++) {
            treeSet.add(new Node(nums.get(i).get(0), i, 0));
        }
        int leftRange = treeSet.first().value;
        int rightRange = treeSet.last().value;
        while (treeSet.size() == N) {
            int minVal = treeSet.first().value;
            int maxVal = treeSet.last().value;
            if (maxVal - minVal < rightRange - leftRange) {
                leftRange = minVal;
                rightRange = maxVal;
            }
            Node minNode = treeSet.pollFirst();
            int arrId = minNode.arrId;
            int index = minNode.index + 1;
            if (nums.get(arrId).size() != index) {
                treeSet.add(new Node(nums.get(arrId).get(index), arrId, index));
            }
        }
        return new int[]{leftRange, rightRange};
    }

}

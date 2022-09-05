import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author wanghu
 * @discrption： 给定两个有序数组 arr1 和 arr2，再给定一个整数k，返回来自 arr1 和 arr2 的两个数相加和最大的前k个，两个数必须分别来自两个数组
 * 按照降序输出
 * [要求]
 * 时间复杂度为 O(klogk)
 * 输入描述：
 * 第一行三个整数N, K分别表示数组 arr1, arr2 的大小，以及需要询问的数
 * 接下来一行N个整数，表示 arr1 内的元素
 * 再接下来一行N个整数，表示 arr2 内的元素
 * 输出描述：
 * 输出K个整数表示答案
 * @create 2022-09-05 11:25
 */
public class P93_TopKSumCrossTwoArrays {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(bufferedReader);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int N = (int) in.nval;
            in.nextToken();
            int k = (int) in.nval;
            int[] arr1 = new int[N];
            int[] arr2 = new int[N];
            for (int i = 0; i < N; i++) {
                in.nextToken();
                arr1[i] = (int) in.nval;
            }
            for (int i = 0; i < N; i++) {
                in.nextToken();
                arr2[i] = (int) in.nval;
            }
            int[] res = topKSum(arr1, arr2, k);
            for (int i = 0; i < k; i++) {
                out.print(res[i] + " ");
            }
            out.println();
            out.flush();
        }
    }

    public static class Node {
        public int index1;
        public int index2;
        public int sum;

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }

    public static class MyComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
        if (arr1 == null || arr2 == null || topK < 1) return null;
        int N = arr1.length;
        int M = arr2.length;
        topK = Math.min(topK, N * M);
        int[] res = new int[topK];
        PriorityQueue<Node> heap = new PriorityQueue<>(new MyComparator());
        HashSet<Long> set = new HashSet<>();
        int index = 0;
        int i1 = N - 1;
        int i2 = M - 1;
        heap.add(new Node(i1, i2, arr1[i1] + arr2[i2]));
        set.add(x(i1, i2, M));
        while (index != topK) {
            Node node = heap.poll();
            res[index++] = node.sum;
            i1 = node.index1;
            i2 = node.index2;
            set.remove(x(i1, i2, M));
            if (i1 - 1 >= 0 && !set.contains(x(i1 - 1, i2, M))) {
                set.add(x(i1 - 1, i2, M));
                heap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
            }
            if (i2 - 1 >= 0 && !set.contains(x(i1, i2 - 1, M))) {
                set.add(x(i1, i2 - 1, M));
                heap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
            }
        }
        return res;
    }

    public static long x(int i, int j, int m) {
        return (long) i * (long) m + (long) j;
    }

}

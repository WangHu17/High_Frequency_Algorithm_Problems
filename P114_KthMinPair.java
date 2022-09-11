import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 第 k 个数值对
 * 长度为N的数组arr，一定可以组成N^2个数字对
 * 例如arr= [3,1,2]，数字对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)
 * 也就是任意两个数都可以，而且自己和自己也算数字对
 * 数字对怎么排序?
 * 第一维数据从小到大，第一维数据一样的，第二维数据也从小到大
 * 所以上面的数值对排序的结果为：(1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
 * 给定一个数组arr，和整数k，返回第k小的数值对。
 * @create 2022-09-11 16:02
 */
public class P114_KthMinPair {

    // O(N*logN)
    public static int[] kthMinPair(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) return null;
        Arrays.sort(arr);
        int firstNum = arr[(k - 1) / N];
        int lessThanFirstNumCount = 0;
        int equalFirstNumCount = 0;
        for (int i = 0; i < N && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum) {
                lessThanFirstNumCount++;
            } else {
                equalFirstNumCount++;
            }
        }
        int rest = k - lessThanFirstNumCount * N;
        return new int[]{firstNum, arr[(rest - 1) / equalFirstNumCount]};
    }

}

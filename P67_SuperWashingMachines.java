/**
 * @author wanghu
 * @discrption： 超级洗衣机
 * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * 给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。
 * 如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
 * @create 2022-08-30 14:11
 */
public class P67_SuperWashingMachines {

    public int findMinMoves(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        int len = arr.length;
        if (sum % len != 0) return -1;
        int avg = sum / len;
        int leftSum = 0;
        int res = 0;
        int leftRest = 0;
        int rightRest = 0;
        for (int i = 0; i < len; i++) {
            leftRest = leftSum - i * avg;
            rightRest = sum - leftSum - arr[i] - (len - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                res = Math.max(res, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                res = Math.max(res, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return res;
    }

}

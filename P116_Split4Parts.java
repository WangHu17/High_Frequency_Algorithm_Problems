import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 分成累加和一样大的4个不同部分
 * 给定一个正数数组arr，长度一定大于6
 * 一定要选3个数字做分割点，从而分出4个部分，并且每部分都有数
 * 分割点的数字直接删除，不属于任何4个部分中的任何一个。
 * 返回有没有可能分出的4个部分累加和一样大
 * 如: [3,2,3,7,4,4,3,1,1,6,7,1,5,2]
 * 可以分成[3,2,3]、[4,4]、[1,1,6]、[1,5,2]。分割点是不算的!
 * @create 2022-09-12 10:55
 */
public class P116_Split4Parts {

    public static boolean canSplits(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        int N = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum =arr[0];
        for (int i=1; i<N; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        int lsum = arr[0];
        for (int s1 = 1; s1 < N-5; s1++) {
            int checkSum = lsum*2 + arr[s1];
            if (map.containsKey(checkSum)){
                int s2 = map.get(checkSum);
                checkSum += lsum + arr[s2];
                if (map.containsKey(checkSum)){
                    int s3 = map.get(checkSum);
                    if (checkSum + lsum + arr[s3] == sum){
                        return true;
                    }
                }
            }
            lsum += arr[s1];
        }
        return false;
    }

}

import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 定义何为step sum？
 * 比如680，680＋68＋6＝754，680的step sum叫754，给定一个正数num，判断它是不是某个数的step sum
 * @create 2022-08-25 10:49
 */
public class P51_IsStepSum {

    public static boolean isStepSum(int stepSum) {
        int l = 0;
        int r = stepSum;
        while (l <= r) {
            int m = l + (r - l) / 2;
            int cur = stepSum(m);
            if (cur == stepSum) {
                return true;
            } else if (cur < stepSum) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }

    public static int stepSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num;
            num /= 10;
        }
        return sum;
    }

    // for test
    public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= numMax; i++) {
            map.put(stepSum(i), i);
        }
        return map;
    }

    // for test
    public static void main(String[] args) {
        int max = 1000000;
        int maxStepSum = stepSum(max);
        HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
        System.out.println("测试开始");
        for (int i = 0; i <= maxStepSum; i++) {
            if (isStepSum(i) ^ ans.containsKey(i)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

}

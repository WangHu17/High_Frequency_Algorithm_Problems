import java.util.HashSet;

/**
 * @author wanghu
 * @discrption： 假设所有字符都是小写字母，大字符串是str，arr是去重的单词表，每个单词都不是空字符串且可以使用任意次。
 * 使用arr中的单词有多少种拼接str的方式，返回方法数。
 * @create 2022-08-19 10:03
 */
public class P42_WordBreak {

    // 暴力递归
    public static int ways1(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0)
            return 0;
        HashSet<String> set = new HashSet<>();
        for (String s : arr) set.add(s);
        return process(str, 0, set);
    }

    public static int process(String str, int i, HashSet<String> set) {
        if (i == str.length()) return 1;
        int ways = 0;
        for (int end = i; end < str.length(); end++) {
            String sub = str.substring(i, end + 1);
            if (set.contains(sub)) {
                ways += process(str, end + 1, set);
            }
        }
        return ways;
    }

    // 动态规划
    public static int ways2(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0)
            return 0;
        HashSet<String> set = new HashSet<>();
        for (String s : arr) set.add(s);
        int N = str.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                String sub = str.substring(i, end + 1);
                if (set.contains(sub)) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    // 前缀树优化
    static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            this.end = false;
            this.nexts = new Node[26];
        }
    }

    public static int ways3(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0)
            return 0;
        Node root = new Node();
        for (String s : arr) {
            Node node = root;
            char[] strs = s.toCharArray();
            int index = 0;
            for (char c : strs) {
                index = c - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }
        int N = str.length();
        char[] chars = str.toCharArray();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                int index = chars[end] - 'a';
                if (cur.nexts[index] == null)break;
                cur = cur.nexts[index];
                if (cur.end){
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    // 以下的逻辑都是为了测试
    public static class RandomSample {
        public String str;
        public String[] arr;

        public RandomSample(String s, String[] a) {
            str = s;
            arr = a;
        }
    }

    // 随机样本产生器
    public static RandomSample generateRandomSample(char[] candidates, int num, int len, int joint) {
        String[] seeds = randomSeeds(candidates, num, len);
        HashSet<String> set = new HashSet<>();
        for (String str : seeds) {
            set.add(str);
        }
        String[] arr = new String[set.size()];
        int index = 0;
        for (String str : set) {
            arr[index++] = str;
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < joint; i++) {
            all.append(arr[(int) (Math.random() * arr.length)]);
        }
        return new RandomSample(all.toString(), arr);
    }

    public static String[] randomSeeds(char[] candidates, int num, int len) {
        String[] arr = new String[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            char[] str = new char[(int) (Math.random() * len) + 1];
            for (int j = 0; j < str.length; j++) {
                str[j] = candidates[(int) (Math.random() * candidates.length)];
            }
            arr[i] = String.valueOf(str);
        }
        return arr;
    }

    public static void main(String[] args) {
        char[] candidates = { 'a', 'b' };
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 30000;
        boolean testResult = true;
        for (int i = 0; i < testTimes; i++) {
            RandomSample sample = generateRandomSample(candidates, num, len, joint);
            int ans1 = ways1(sample.str, sample.arr);
            int ans2 = ways2(sample.str, sample.arr);
            int ans3 = ways3(sample.str, sample.arr);
            if (ans1 != ans2 || ans3 != ans1 || ans2 != ans3) {
                testResult = false;
            }
        }
        System.out.println(testTimes + "次随机测试是否通过：" + testResult);
    }


}

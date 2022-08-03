import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author wanghu
 * @discrption： 给定数组 hard 和 money，长度都为 N，hard[i] 表示 i 号工作的难度，money[i] 表示 i 号工作的收入。
 * 给定数组 ability，长度都为M，ability[j] 表示 j 号人的能力。每一号工作，都可以提供无数的岗位，难度和收入都一样，
 * 但是人的能力必须>=这份工作的难度，才能上班。返回一个长度为 M 的数组 ans, ans[j] 表示 j 号人能获得的最好收入。
 * @create 2022-08-03 9:39
 */
public class P8_ChooseWork {

    class Job {
        public int money;
        public int hard;

        public Job(int money, int hard) {
            this.money = money;
            this.hard = hard;
        }
    }

    class MyComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? (o1.money - o2.money) : (o2.money - o1.money);
        }
    }

    public int[] getMoney(Job[] jobs, int[] ability) {
        Arrays.sort(jobs, new MyComparator());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(jobs[0].hard, jobs[0].money);
        Job pre = jobs[0];
        for (int i = 1; i < jobs.length; i++) {
            if (jobs[i].hard != pre.hard && jobs[i].money > pre.money) {
                map.put(jobs[i].hard, jobs[i].money);
            }
        }
        int[] res = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            Integer key = map.floorKey(ability[i]);
            res[i] = key != null ? map.get(key) : 0;
        }
        return res;
    }

}

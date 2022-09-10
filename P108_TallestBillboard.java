import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 最高的广告牌
 * 你正在安装一个广告牌并且想要得到最大的高度。广告牌将有两个钢支持，两边各一个。每个钢支架必须同等高度。给你棒的集合，可以焊接在一起。
 * 例如，如果您有棒的长度1,2,3，你可以焊接在一起变成长度为6。返回最大可能的广告牌安装的高度。如果你不能支持广告牌，返回0。
 * @create 2022-09-10 11:15
 */
public class P108_TallestBillboard {

    public int tallestBillboard(int[] rods) {
        HashMap<Integer, Integer> dp = new HashMap<>(), cur;
        dp.put(0, 0);
        for (int num : rods) {
            if (num != 0) {
                cur = new HashMap<>(dp);
                for (Integer d : cur.keySet()) {
                    int base = cur.get(d);
                    // 放入较大的
                    dp.put(d + num, Math.max(base, dp.getOrDefault(d + num, 0)));
                    // 放入较小的
                    int oldBase = dp.getOrDefault(Math.abs(d - num), 0);
                    if (d >= num) {
                        dp.put(d - num, Math.max(base + num, oldBase));
                    } else {
                        dp.put(num - d, Math.max(base + d, oldBase));
                    }
                }
            }
        }
        return dp.get(0);
    }

}

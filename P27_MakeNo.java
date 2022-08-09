/**
 * @author wanghu
 * @discrption： 生成长度为 size 的达标数组，什么叫达标？
 * 达标：对于任意的 i＜k＜j，满足［i］＋［j］！＝［k］＊2，给定一个正数 size，返回长度为 size 的达标数组。
 * @create 2022-08-09 9:27
 */
public class P27_MakeNo {

    public static int[] makeNo(int size) {
        if (size == 1) return new int[]{1};
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        int[] res = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            res[index] = base[index] * 2 - 1;
        }
        for (int i = 0; index < size; i++, index++) {
            res[index] = base[i] * 2;
        }
        return res;
    }

    // 检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = makeNo(N);
            if (!isValid(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
        System.out.println(isValid(makeNo(1042)));
        System.out.println(isValid(makeNo(2981)));
    }

}

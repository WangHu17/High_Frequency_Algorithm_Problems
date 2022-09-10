/**
 * @author wanghu
 * @discrption： 一个数组中如果有重复的数字超过了数组的一半，这个数就是水王数。如果有水王数，打印它，如果没有，打印没有。
 * 要求时间复杂度 O(N)，空间复杂度 O(1)
 * @create 2022-09-10 16:03
 */
public class P111_FindKMajority {

    public static void printHalfMajor(int[] arr) {
        int cand = 0;
        int hp = 0;
        for (int n : arr) {
            if (hp == 0) {
                cand = n;
                hp = 1;
            }else if (n == cand) {
                hp++;
            }else {
                hp--;
            }
        }
        if (hp <= 0) System.out.println("没有水王数");
        hp = 0;
        for (int n:arr) {
            if (n == cand)
                hp++;
        }
        if (hp > arr.length / 2) System.out.println("水王数是：" + cand);
        else System.out.println("没有水王数");
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
        printHalfMajor(arr);
    }

}

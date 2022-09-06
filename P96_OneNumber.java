/**
 * @author wanghu
 * @discrption： 1 字符出现的次数
 * 给定一个正数N，比如 N=13，在纸上把所有数都列出来如下:
 * 1 2 3 4 5 6 7 8 9 10 11 12 13
 * 可以数出1这个字符出现了6次，给定一个正数N，如果把 1-N 都列出来，返回1这个字符出现的次数。
 * @create 2022-09-06 10:04
 */
public class P96_OneNumber {

    public int countDigitOne(int n) {
        if (n < 1) return 0;
        int len = (n + "").length();
        if (len == 1) return 1;
        int temp = (int) Math.pow(10, len - 1);
        int first = n / temp;
        int firstOneNum = first == 1 ? n % temp + 1 : temp;
        int restOneNum = first * (len - 1) * (temp / 10);
        return firstOneNum + restOneNum + countDigitOne(n % temp);
    }

}

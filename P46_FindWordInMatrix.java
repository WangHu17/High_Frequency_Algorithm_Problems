/**
 * @author wanghu
 * @discrption： 给定一个 char[] matrix，也就是 char 类型的二维数组，再给定一个字符串 word，可以从任何一个某个位置出发，可以走上下左右，能不能找到 word?
 * char[] m={{'a','b','z'},
 * {'c','d','o'},
 * {'f' ,'e','o'}}
 * 设定1：可以走重复路的情况下，返回能不能找到，比如，word="zoooz"，是可以找到的，z->0->0->0->z
 * 设定2：不可以走重复路的情况下，返回能不能找到，比如，word="zoooz"，是不可以找到的。
 * @create 2022-08-23 11:03
 */
public class P46_FindWordInMatrix {

    public static boolean findWord(char[][] m, String word) {
        if (word == null || word.length() == 0) return true;
        if (m == null || m.length == 0 || m[0].length == 0) return false;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (noLoop(m, word.toCharArray(), i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 可以走重复路
    public static boolean canLoop(char[][] m, char[] word, int i, int j, int k) {
        if (k == word.length) {
            return true;
        }
        if (i < 0 || i > m.length - 1 || j < 0 || j > m[0].length - 1 || m[i][j] != word[k]) return false;
        boolean ans = false;
        if (canLoop(m, word, i - 1, j, k + 1) || canLoop(m, word, i + 1, j, k + 1) ||
                canLoop(m, word, i, j + 1, k + 1) || canLoop(m, word, i, j - 1, k + 1)) {
            ans = true;
        }
        return ans;
    }

    // 不可以走重复路
    public static boolean noLoop(char[][] m, char[] word, int i, int j, int k) {
        if (k == word.length) {
            return true;
        }
        if (i < 0 || i > m.length - 1 || j < 0 || j > m[0].length - 1 || m[i][j] != word[k]) return false;
        m[i][j] = 0;
        boolean ans = false;
        if (canLoop(m, word, i - 1, j, k + 1) || canLoop(m, word, i + 1, j, k + 1) ||
                canLoop(m, word, i, j + 1, k + 1) || canLoop(m, word, i, j - 1, k + 1)) {
            ans = true;
        }
        m[i][j] = word[k];
        return ans;
    }

    public static void main(String[] args) {
        char[][] m = {  { 'a', 'b', 'z' },
                        { 'c', 'd', 'o' },
                        { 'f', 'e', 'o' }, };
        String word1 = "zoooz";
        String word2 = "zoo";
        System.out.println(findWord(m, word1));
        System.out.println(findWord(m, word2));
    }

}

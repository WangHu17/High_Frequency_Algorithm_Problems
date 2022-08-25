import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghu
 * @discrption： 无效括号串变有效的所有可能结果
 * @create 2022-08-24 11:04
 */
public class P48_RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        ArrayList<String> res = new ArrayList<>();
        remove(s, res, 0, 0, new char[]{'(', ')'});
        return res;
    }

    public void remove(String s, List<String> res, int checkIndex, int deleteIndex, char[] par) {
        for (int count = 0, i = checkIndex; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                count++;
            }
            if (s.charAt(i) == par[1]) {
                count--;
            }
            if (count < 0) {
                for (int j = deleteIndex; j <= i; j++) {
                    if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) {
                        remove(s.substring(0, j) + s.substring(j + 1, s.length()), res, i, j, par);
                    }
                }
                return;
            }
        }
        String reverse = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            remove(reverse, res, 0, 0, new char[]{')', '('});
        } else {
            res.add(reverse);
        }
    }

}

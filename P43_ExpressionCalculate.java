import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 给定一个字符串表示一个公式，公式里有整数、加减乘除符号和左右括号，返回这个公式的计算结果。
 * @create 2022-08-22 9:26
 */
public class P43_ExpressionCalculate {

    public int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    public int[] f(char[] str, int i) {
        Stack<String> stack = new Stack<>();
        int[] res = null;
        int cur = 0;
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + (str[i++] - '0');
            } else if (str[i] != '(') {
                addNum(stack, cur);
                stack.push(String.valueOf(str[i++]));
                cur = 0;
            } else {
                res = f(str, i + 1);
                cur = res[0];
                i = res[1] + 1;
            }
        }
        addNum(stack, cur);
        return new int[]{getNum(stack), i};
    }

    private void addNum(Stack<String> stack, int num) {
        if (!stack.isEmpty()) {
            String top = stack.pop();
            if (top.equals("+") || top.equals("-")) {
                stack.push(top);
            } else {
                int cur = Integer.parseInt(stack.pop());
                num = top.equals("*") ? (num * cur) : (cur / num);
            }
        }
        stack.push(String.valueOf(num));
    }

    private int getNum(Stack<String> stack) {
        int res = 0;
        int num = 0;
        String cur = null;
        boolean add = true;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (cur.equals("+")){
                add = true;
            }else if (cur.equals("-")){
                add = false;
            }else {
                num = Integer.parseInt(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

}

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 3、最接近 num 的2的某次方
 * 给定一个非负整数num，如何不用循环语句，返回>=num，并且离num最近的2的某次方。
 * @create 2022-08-01 10:25
 */
public class P3 {

    public int f(int num) {
        num--;
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        return num < 0 ? 1 : (num + 1);
    }

    @Test
    public void test(){
        System.out.println(f(125));
    }

}

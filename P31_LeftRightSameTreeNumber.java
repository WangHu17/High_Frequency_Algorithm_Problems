import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wanghu
 * @discrption： 如果一个节点X，它左树结构和右树结构完全一样，那么我们说以X为头的子树是相等子树，给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树。
 * @create 2022-08-09 14:32
 */
public class P31_LeftRightSameTreeNumber {

    // 方法一：暴力递归 O(N * logN)
    public int sameNumber1(TreeNode head) {
        if (head == null) return 0;
        return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) ? 1 : 0);
    }

    public boolean same(TreeNode node1, TreeNode node2) {
        if (node1 == null ^ node2 == null) return false;
        if (node1 == null && node2 == null) return true;
        return node1.val == node2.val && same(node1.left, node2.left) && same(node1.right, node2.right);
    }

    // 方法二：序列化 + hashCode O(N)
    class Info {
        int ans;
        String hashcode;

        public Info(int ans, String hashcode) {
            this.ans = ans;
            this.hashcode = hashcode;
        }
    }

    public int sameNumber2(TreeNode head) {
        String algorithm = "SHA-256";
        Hash hash = new Hash(algorithm);
        return process(head, hash).ans;
    }

    public Info process(TreeNode head, Hash hash) {
        if (head == null) {
            return new Info(0, hash.hashCode("#,"));
        }
        Info l = process(head.left, hash);
        Info r = process(head.right, hash);
        int ans = (l.hashcode.equals(r.hashcode) ? 1 : 0) + l.ans + r.ans;
        String hashcode = hash.hashCode(String.valueOf(head.val)) + "," + l.hashcode + r.hashcode;
        return new Info(ans, hashcode);
    }

    @Test
    public void test() {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            TreeNode head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameNumber1(head);
            int ans2 = sameNumber2(head);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");
    }

    public TreeNode randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        TreeNode head = Math.random() < 0.2 ? null : new TreeNode((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }

}

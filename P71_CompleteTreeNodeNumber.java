/**
 * @author wanghu
 * @discrption： 完全二叉树的节点数
 * 给定一棵完全二叉树，返回这棵树的节点个数，要求时间复杂度小于 O(树的节点数)。
 * @create 2022-08-31 10:40
 */
public class P71_CompleteTreeNodeNumber {

    public int countNodes(TreeNode head) {
        if (head == null) return 0;
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    public int bs(TreeNode head, int level, int h) {
        if (level == h) return 1;
        if (mostLeftLevel(head.right, level + 1) == h) {
            return (1 << (h - level)) + bs(head.right, level + 1, h);
        } else {
            return (1 << (h - level - 1)) + bs(head.left, level + 1, h);
        }
    }

    public int mostLeftLevel(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

}

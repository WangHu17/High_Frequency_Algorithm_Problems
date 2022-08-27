/**
 * @author wanghu
 * @discrption： 给定一棵搜索二叉树头节点，转化成首尾相接的有序双向链表。
 * @create 2022-08-26 14:33
 */
public class P55_BSTToDoublyList {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node treeToDoublyList(Node head) {
        if (head == null)return null;
        Info info = process(head);
        info.end.right = info.start;
        info.start.left = info.end;
        return info.start;
    }

    static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(null, null);
        }
        Info lInfo = process(node.left);
        Info rInfo = process(node.right);
        if (lInfo.end != null) {
            lInfo.end.right = node;
        }
        node.left = lInfo.end;
        node.right = rInfo.start;
        if (rInfo.start != null) {
            rInfo.start.left = node;
        }
        Node start = lInfo.start != null ? lInfo.start : node;
        Node end = rInfo.end != null ? rInfo.end : node;
        return new Info(start, end);
    }

}

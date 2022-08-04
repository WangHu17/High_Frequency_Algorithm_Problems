import java.util.*;

/**
 * @author wanghu
 * @discrption： 给定三个参数：
 * 二叉树的头节点 head，树上某个节点 target，正数 K。从 target 开始，可以向上走或者向下走返回与 target 的距离是 K 的所有节点。
 * @create 2022-08-04 11:31
 */
public class P14_DistanceKNodes {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static ArrayList<Node> distanceKNodes(Node root, Node target, int k) {
        HashMap<Node, Node> parent = new HashMap<>();
        parent.put(root, null);
        createParent(root, parent);
        LinkedList<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);
        int level = 0;
        ArrayList<Node> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0){
                Node cur = queue.poll();
                if (level == k){
                    res.add(cur);
                }
                if (cur.left != null && !visited.contains(cur.left)){
                    visited.add(cur.left);
                    queue.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)){
                    visited.add(cur.right);
                    queue.add(cur.right);
                }
                if (parent.get(cur) != null && !visited.contains(parent.get(cur))){
                    visited.add(parent.get(cur));
                    queue.add(parent.get(cur));
                }
            }
            level++;
            if (level > k)break;
        }
        return res;
    }

    private static void createParent(Node node, HashMap<Node, Node> parent) {
        if (node == null) return;
        if (node.left != null) {
            parent.put(node.left, node);
            createParent(node.left, parent);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            createParent(node.right, parent);
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

    }

}

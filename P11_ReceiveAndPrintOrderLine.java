import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 链路中传输的信号可能不是有序的，当后面的信号先到，先缓存着不播出，开头的信号到了就播出，连着的信号按序播出，如果中间断了有信号还没到，
 * 到了的排在后面的信号等着。
 * @create 2022-08-03 11:07
 */
public class P11_ReceiveAndPrintOrderLine {

    public static class Node {
        String info;
        Node next;

        public Node(String info) {
            this.info = info;
        }
    }

    public static class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String info) {
            if (num < 1) return;
            Node node = new Node(info);
            headMap.put(num, node);
            tailMap.put(num, node);
            if (headMap.containsKey(num + 1)) {
                node.next = headMap.get(num + 1);
                headMap.remove(num + 1);
                tailMap.remove(num);
            }
            if (tailMap.containsKey(num - 1)) {
                tailMap.get(num - 1).next = node;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            if (waitPoint == num) {
                print();
            }
        }

        public void print() {
            Node node = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            while (node != null) {
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint - 1);
        }
    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1
        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10
        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11

    }

}

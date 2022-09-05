import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： LRU 缓存
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * @create 2022-09-05 14:07
 */
public class P94_LRUCache<K, V> {

    private MyCache<K, V> cache;

    public P94_LRUCache(int capacity) {
        cache = new MyCache<>(capacity);
    }

    public V get(K key) {
        V v = cache.get(key);
        return v;
    }

    public void put(K key, V val) {
        cache.set(key, val);
    }

    static class Node<K, V> {
        public K key;
        public V val;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    static class NodeDoubleLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public NodeDoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void addNode(Node<K, V> node) {
            if (node == null) return;
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.last = tail;
            }
            tail = node;
        }

        public void moveNodeToTail(Node<K, V> node) {
            if (node == null)return;
            if (tail == node)return;
            if (head == node) {
                head = head.next;
                head.last = null;
            }else {
                node.next.last = node.last;
                node.last.next = node.next;
            }
            tail.next = node;
            node.last = tail;
            node.next = null;
            tail = node;
        }

        public Node<K, V> removeHead() {
            Node<K, V> res = head;
            if (head == tail){
                head = null;
                tail = null;
            }else {
                head = head.next;
                res.next = null;
                head.last = null;
            }
            return res;
        }
    }

    static class MyCache<K, V> {

        private HashMap<K, Node<K, V>> keyNodeMap;
        private NodeDoubleLinkedList nodeList;
        private final int capacity;

        public MyCache(int cap) {
            keyNodeMap = new HashMap<>();
            nodeList = new NodeDoubleLinkedList();
            capacity = cap;
        }

        public V get(K key) {
            if (keyNodeMap.containsKey(key)) {
                Node<K, V> res = keyNodeMap.get(key);
                nodeList.moveNodeToTail(res);
                return res.val;
            }
            return null;
        }

        public void set(K key, V val) {
            if (!keyNodeMap.containsKey(key)){
                Node<K, V> node = new Node<>(key, val);
                keyNodeMap.put(key, node);
                nodeList.addNode(node);
                if (keyNodeMap.size() == capacity + 1){
                    removeMostUnusedNode();
                }
            }else {
                Node<K, V> node = keyNodeMap.get(key);
                node.val = val;
                nodeList.moveNodeToTail(node);
            }
        }

        private void removeMostUnusedNode() {
            Node node = nodeList.removeHead();
            keyNodeMap.remove(node.key);
        }
    }

}

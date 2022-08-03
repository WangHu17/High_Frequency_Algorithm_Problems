import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： setAll(value) 把表中所有的 key 的 值都设成 value，并且时间复杂度 O(1)。
 * @create 2022-08-03 10:27
 */
public class P10_SetAll {

    class MyValue<V> {
        public V value;
        public long time;

        public MyValue(V value, long time) {
            this.value = value;
            this.time = time;
        }
    }

    class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> map;
        private long time;
        private MyValue<V> setAll;

        public MyHashMap() {
            map = new HashMap<>();
            time = 0;
            setAll = new MyValue<>(null, -1);
        }

        public void put(K key, V value) {
            map.put(key, new MyValue<>(value, time++));
        }

        public void setAll(V value){
            setAll = new MyValue<>(value, time++);
        }

        public V get(K key) {
            if (!map.containsKey(key)) {
                return null;
            }
            if (map.get(key).time > setAll.time) {
                return map.get(key).value;
            }else {
                return setAll.value;
            }
        }
    }

}

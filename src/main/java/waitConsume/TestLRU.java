package waitConsume;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther xzl on 13:37 2018/6/20
 */
public class TestLRU {
    public static void main(String[] args) {
        String name = "我";
        System.out.println("hashCode是："+name.hashCode());
        System.out.println("===========================FIFO LinkedHashMap默认实现===========================");
        final int cacheSize = 5;
        LinkedHashMap<Integer, String> lru = new LinkedHashMap<Integer, String>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > cacheSize;
            }
        };

        lru.put(1, "11");
        lru.put(2, "12");
        lru.put(3, "13");
        lru.put(4, "14");
        lru.put(5, "15");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
    }
}

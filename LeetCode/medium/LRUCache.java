package LeetCode.medium;

import CTCI.LogUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRUCache {

    private final int _maxCapacity;
    private int _capacity;

    private Map<Integer, Node> _cache = new HashMap<>();
    private List<Node> _order = new LinkedList<>();

    private LRUCache(int capacity) {
        _maxCapacity = capacity;
    }

    public int get(int key) {
        Node node = _cache.get(key);
        if (node != null) {
            updateLRU(node);
        }
        return node != null ? node._value : -1;
    }

    private void updateLRU(Node node) {
        _order.remove(node);
        _order.add(node);
    }

    public void put(int key, int value) {
        Node existing = _cache.get(key);
        if (existing != null) {
            existing._value = value;
            updateLRU(existing);
        } else {
            evict();
            Node newNode = new Node(key, value);
            // add
            _cache.put(key, newNode);
            _order.add(newNode);
            // update
            _capacity++;
        }
    }

    // only if needed
    private void evict() {
        if (_capacity == _maxCapacity) {
            Node node = _order.remove(0);
            _cache.remove(node._key);
            _capacity--;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1); // 1,1
        lruCache.put(3, 3); // 1,1 | 3,3
        lruCache.put(5, 5); // 1,1 | 3,3 | 5,5
        lruCache.put(7, 7); // 3,3 | 5,5 | 7,7
        LogUtils.logMessage("expecting 5 : " + lruCache.get(5)); // 3,3 | 7,7 | 5,5
        LogUtils.logMessage("expecting 3 : " + lruCache.get(3)); // 7,7 | 5,5 | 3,3
        lruCache.put(9, 9); // 5,5 | 3,3 | 9,9
        LogUtils.logMessage("expecting -1 : " + lruCache.get(1)); // 3,3 | 9,9 | 5,5 -- evicted!
        LogUtils.logMessage("expecting 5 : " + lruCache.get(5)); // 3,3 | 9,9 | 5,5
        LogUtils.logMessage("expecting 3 : " + lruCache.get(3)); // 9,9 | 5,5 | 3,3
        LogUtils.logMessage("expecting -1 : " + lruCache.get(7)); // 9,9 | 5,5 | 3,3 -- evicted
        LogUtils.logMessage("expecting 9 : " + lruCache.get(9)); // 9,9 | 5,5 | 3,3
        LogUtils.logMessage("expecting -1 : " + lruCache.get(-1)); // 9,9 | 5,5 | 3,3 -- does not exist
    }

    class Node {
        int _key;
        int _value;

        Node(int k, int v) {
            _key = k;
            _value = v;
        }
    }
}

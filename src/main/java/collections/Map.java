package collections;

import collections.impl.HashMap.Entry;

public interface Map<K, V> {

    boolean insert(K key, V value);

    V get(K key);

    Entry delete(K key);
}
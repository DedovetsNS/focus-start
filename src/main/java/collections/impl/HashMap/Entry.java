package collections.impl.HashMap;

public class Entry<K,V> {
    private K key;
    private V value;
    private Entry next;
    private Entry previous;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    void setValue(V value) {
        this.value = value;
    }

    Entry getNext() {
        return next;
    }

    void setNext(Entry next) {
        this.next = next;
    }

    Entry getPrevious() {
        return previous;
    }

    void setPrevious(Entry previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

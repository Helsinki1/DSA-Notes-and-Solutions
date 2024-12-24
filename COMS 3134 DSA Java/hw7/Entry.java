/**
 * Class for encapsulating a key-value entry into a hash map.
 * @author Brian S. Borowski
 * @version 1.0 October 19, 2022
 */
public class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;

    /**
     * Creates a key-value pair.
     * @param key   the specified key to encapsulate
     * @param value the value to associate with the key
     */
    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "<" + key + ", " + value + ">";
    }
}

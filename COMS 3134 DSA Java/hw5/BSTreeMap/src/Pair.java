/**
 * Class for encapsulating a key-value pair.
 * @author Brian S. Borowski
 * @version 1.0 October 19, 2022
 */
public class Pair<K, V> {
    K key;
    V value;

    /**
     * Creates a key-value pair.
     * @param key   the specified key to encapsulate
     * @param value the value to associate with the key
     */
    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

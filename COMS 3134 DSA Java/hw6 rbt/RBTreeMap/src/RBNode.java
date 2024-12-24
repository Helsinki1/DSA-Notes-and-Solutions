/**
 * Class for an RBNode containing a key-value mapping. Each node contains a
 * reference to the left child, right child, and parent, as well as a field
 * containing the color of the node.
 * @author Brian S. Borowski
 * @version 1.0 February 17, 2024
 */
public class RBNode<K, V> extends Node<K, V> {
    public final static byte BLACK = 0, RED = 1;
    public final static String[] COLOR = {"B", "R"};

    byte color = RED;  // Used only in RBTreeMap, defaults to red.

    /**
     * Creates a node with a key-value mapping.
     * @param key   the specified key to uniquely represent this Node
     * @param value the specified value to associate with the key
     */
    public RBNode(K key, V value) {
        super(key, value);
    }

    /**
     * Returns a reference to the left child of this Node.
     * @return a reference to the left child of this Node
     */
    public final RBNode<K, V> getLeft() {
        return (RBNode<K, V>)super.getLeft();
    }

    /**
     * Returns a reference to the right child of this Node.
     * @return a reference to the right child of this Node
     */
    public final RBNode<K, V> getRight() {
        return (RBNode<K, V>)super.getRight();
    }

    /**
     * Returns a reference to the parent of this Node.
     * @return a reference to the parent of this Node
     */
    public final RBNode<K, V> getParent() {
        return (RBNode<K, V>)super.getParent();
    }

    /**
     * Returns a String representation of the Node with the key and value
     * inside angled brackets.
     * @return a String representation of the Node
     */
    public String toString() {
        return "<" + key + ", " + value + ", " + COLOR[color] + ">";
    }
}

/**
 * Class for a Node containing a key-value mapping. Each node contains a
 * reference to the left child, right child, and parent.
 * @author Brian S. Borowski
 * @version 1.1 February 17, 2024
 */
public class Node<K, V> extends Entry<K, V> {
    private Node<K, V> left, right, parent;

    /**
     * Creates a node with a key-value mapping.
     * @param key   the specified key to uniquely represent this Node
     * @param value the specified value to associate with the key
     */
    public Node(K key, V value) {
        super(key, value);
    }

    /**
     * Returns a reference to the left child of this Node.
     * @return a reference to the left child of this Node
     */
    public Node<K, V> getLeft() {
        return this.left;
    }

    /**
     * Returns a reference to the right child of this Node.
     * @return a reference to the right child of this Node
     */
    public Node<K, V> getRight() {
        return this.right;
    }

    /**
     * Returns a reference to the parent of this Node.
     * @return a reference to the parent of this Node
     */
    public Node<K, V> getParent() {
        return this.parent;
    }

    /**
     * Sets the reference of the left child of this Node.
     * @param node reference to a Node that will become the left child of this
     *             Node
     */
    public void setLeft(Node<K, V> node) {
        this.left = node;
    }

    /**
     * Sets the reference of the right child of this Node.
     * @param node reference to a Node that will become the right child of this
     *             Node
     */
    public void setRight(Node<K, V> node) {
        this.right = node;
    }

    /**
     * Sets the reference of the parent of this Node.
     * @param node reference to a Node that will become the parent of this Node
     */
    public void setParent(Node<K, V> node) {
        this.parent = node;
    }

    /**
     * Returns a String representation of the Node with the key and value
     * inside angled brackets.
     * @return a String representation of the Node
     */
    public String toString() {
        return "<" + key + ", " + value + ">";
    }
}

/**
 * Class that implements a red-black tree which implements the MyMap interface.
 * @author Brian S. Borowski
 * @version 1.2.2 March 8, 2024
 */
public class RBTreeMap<K extends Comparable<K>, V> extends BSTreeMap<K, V>
        implements MyMap<K, V> {

    /**
     * Creates an empty red-black tree map.
     */
    public RBTreeMap() { }

    /**
     * Creates a red-black tree map from the array of key-value pairs.
     * @param elements an array of key-value pairs
     */
    public RBTreeMap(Pair<K, V>[] elements) {
        insertElements(elements);
    }

    /**
     * Creates a red-black tree map of the given key-value pairs. If
     * sorted is true, a balanced tree will be created via a divide-and-conquer
     * approach. If sorted is false, the pairs will be inserted in the order
     * they are received, and the tree will be rotated to maintain the red-black
     * tree properties.
     * @param elements an array of key-value pairs
     */
    public RBTreeMap(Pair<K, V>[] elements, boolean sorted) {
        if (!sorted) {
            insertElements(elements);
        } else {
            root = createBST(elements, 0, elements.length - 1);
        }
    }

    /**
     * Recursively constructs a balanced binary search tree by inserting the
     * elements via a divide-snd-conquer approach. The middle element in the
     * array becomes the root. The middle of the left half becomes the root's
     * left child. The middle element of the right half becomes the root's right
     * child. This process continues until low > high, at which point the
     * method returns a null Node.
     * All nodes in the tree are black down to and including the deepest full
     * level. Nodes below that deepest full level are red. This scheme ensures
     * that all paths from the root to the nulls contain the same number of
     * black nodes.
     * @param pairs an array of <K, V> pairs sorted by key
     * @param low   the low index of the array of elements
     * @param high  the high index of the array of elements
     * @return      the root of the balanced tree of pairs
     */
    protected Node<K, V> createBST(Pair<K, V>[] pairs, int low, int high) {
        return createBSTHelper(
                pairs, low, high, null, 0,
                (int)(Math.log(pairs.length + 1) / Math.log(2)));
    }

    private Node<K, V> createBSTHelper(
            Pair<K, V>[] pairs, int low, int high, Node<K, V> parent, int level,
            int redLevel) {
        if (low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        Pair<K, V> pair = pairs[mid];
        RBNode<K, V> node = new RBNode<>(pair.key, pair.value);
        node.color = (level == redLevel) ? RBNode.RED : RBNode.BLACK;
        node.setParent(parent);
        node.setLeft(
            createBSTHelper(
                    pairs, low, mid - 1, node, level + 1, redLevel));
        node.setRight(
            createBSTHelper(
                    pairs, mid + 1, high, node, level + 1, redLevel));
        size++;
        return node;
    }

    /**
     * Associates the specified value with the specified key in this map. If the
     * map previously contained a mapping for the key, the old value is replaced
     * by the specified value.
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    @Override
    public V put(K key, V value) {
        Node<K, V> x = root, y = null;
        while (x != null) {
            y = x;
            int comparison = key.compareTo(x.key);
            if (comparison < 0) {
                x = x.getLeft();
            } else if (comparison > 0) {
                x = x.getRight();
            } else {
                // The key was found in the tree. Return the previous value
                // associated with the key. There's no reason to balance.
                V oldValue = x.value;
                x.value = value;
                return oldValue;
            }
        }
        RBNode<K, V> z = new RBNode<>(key, value);
        z.setParent(y);
        if (y == null) {
            root = z;
        } else if (key.compareTo(y.key) < 0) {
            y.setLeft(z);
        } else {
            y.setRight(z);
        }
        insertFixup(z);
        size++;
        return null;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V remove(K key) {
        // TODO, otherwise return null.
        return null;
    }

    /**
     * Fixup method described on p. 339 of CLRS, 4e.
     */
    private void insertFixup(RBNode<K, V> z) {
        while (z != root && z.getParent().color == RBNode.RED) {
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                RBNode<K, V> y = z.getParent().getParent().getRight();
                if (y != null && y.color == RBNode.RED) {
                    z.getParent().color = RBNode.BLACK;
                    y.color = RBNode.BLACK;
                    z.getParent().getParent().color = RBNode.RED;
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRight()) {
                        z = z.getParent();
                        leftRotate(z);
                    }
                    z.getParent().color = RBNode.BLACK;
                    z.getParent().getParent().color = RBNode.RED;
                    rightRotate(z.getParent().getParent());
                }
            } else {
                RBNode<K, V> y = z.getParent().getParent().getLeft();
                if (y != null && y.color == RBNode.RED) {
                    z.getParent().color = RBNode.BLACK;
                    y.color = RBNode.BLACK;
                    z.getParent().getParent().color = RBNode.RED;
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeft()) {
                        z = z.getParent();
                        rightRotate(z);
                    }
                    z.getParent().color = RBNode.BLACK;
                    z.getParent().getParent().color = RBNode.RED;
                    leftRotate(z.getParent().getParent());
                }
            }
        }
        ((RBNode<K, V>)root).color = RBNode.BLACK;
    }

    /**
     * Left-rotate method described on p. 336 of CLRS, 4e.
     */
    private void leftRotate(Node<K, V> x) {
        Node<K, V> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    /**
     * Right-rotate method described on p. 336 of CLRS, 4e.
     */
    private void rightRotate(Node<K, V> x) {
        Node<K, V> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }
}

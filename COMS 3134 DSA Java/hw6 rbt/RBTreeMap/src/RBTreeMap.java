/**
 * Class that implements a red-black tree which implements the MyMap interface.
 * @author Brian S. Borowski
 * @version 1.2.1 March 5, 2024
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
        if(low > high) return null;
        int index = low + (high - low)/2;
        Node<K,V> root = new RBNode<>(pairs[index].key, pairs[index].value);
        Node<K,V> Lchild = createBST(pairs, low, index-1);
        Node<K,V> Rchild = createBST(pairs, index+1, high);
        root.setLeft(Lchild);
        root.setRight(Rchild);
        if(Lchild != null) Lchild.setParent(root);
        if(Rchild != null) Rchild.setParent(root);
        if(Math.log(high - low)/Math.log(2) < 2) ((RBNode<K,V>)root).color = 0; // black = 0, red = 1
        else ((RBNode<K,V>)root).color = 1;
        size++;
        return root;
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
        if(root == null){
            root = new RBNode<K,V>(key,value);
            ((RBNode<K,V>)root).color = 0;
            root.setLeft(null);
            root.setRight(null);
            size++;
            return null;
        }
        RBNode<K,V> x = (RBNode<K,V>)root;
        RBNode<K,V> y = null;
        RBNode<K,V> z = new RBNode<>(key, value);
        while(x != null){
            y = x;
            if(key.compareTo(x.key) == 0){
                V out = x.value;
                x.value = value;
                return out;
            }
            else if(key.compareTo(x.key) < 0){
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        z.setParent(y);
        if(y == null) root = z;
        else if(key.compareTo(y.key) < 0) y.setLeft(z);
        else y.setRight(z);
        z.setLeft(null);
        z.setRight(null);
        z.color = 1;
        size++;
        insertFixup(z);
        return null;
    }

    public void rb_transplant(Node<K,V> a, Node<K,V> b){
        if (a.getParent() == null){
            root = b;
        } else if(a == a.getParent().getLeft()){
            a.getParent().setLeft(b);
        } else{
            a.getParent().setRight(b);
        }
        b.setParent(a.getParent());
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V remove(K key) {
        RBNode<K,V> z = (RBNode<K,V>)iterativeSearch(key);
        RBNode<K,V> y = z;
        RBNode<K,V> x;
        int y_og_color = y.color;
        if(z.getLeft() == null){
            x = z.getRight();
            rb_transplant(z, z.getRight());
        }else if(z.getRight() == null){
            x = z.getLeft();
            rb_transplant(z, z.getLeft());
        }else{
            y = (RBNode<K,V>) treeMinimum(z.getRight());
            y_og_color = y.color;
            x = y.getRight();
            if(y != z.getRight()){
                rb_transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }else{
                x.setParent(y);
            }
            rb_transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.color = z.color;
        }
        if(y_og_color == 0) deleteFixup(x);
        return (z==null)? null : z.value;
    }

    /**
     * Fixup method described on p. 351 of CLRS, 4e.
     */
    private void deleteFixup(RBNode<K, V> x) {
        RBNode<K,V> w = null;
        while(x != root && x.color==0){
            if(x==x.getParent().getLeft()){
                w = x.getParent().getRight();
                if(w.color == 1){
                    w.color = 0;
                    x.getParent().color = 1;
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }
                if(w.getLeft() != null && w.getRight() != null && w.getLeft().color==0 && w.getRight().color==0){
                    w.color = 1;
                    x = x.getParent();
                }else{
                    if(w.getRight().color == 0){
                        w.getLeft().color = 0;
                        w.color = 1;
                        rightRotate(w);
                        w = x.getParent().getRight();
                    }
                    w.color = x.getParent().color;
                    x.getParent().color = 0;
                    w.getRight().color = 0;
                    leftRotate(x.getParent());
                    x = (RBNode<K,V>)root;
                }
            }else{
                w = x.getParent().getLeft();
                if(w.color == 1){
                    w.color = 0;
                    x.getParent().color = 1;
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                }
                if(w.getRight().color==0 && w.getLeft().color==0){
                    w.color = 1;
                    x = x.getParent();
                }else{
                    if(w.getLeft().color == 0){
                        w.getRight().color = 0;
                        w.color = 1;
                        leftRotate(w);
                        w = x.getParent().getLeft();
                    }
                    w.color = x.getParent().color;
                    x.getParent().color = 0;
                    w.getLeft().color = 0;
                    rightRotate(x.getParent());
                    x = (RBNode<K,V>)root;
                }
            }
        }
        x.color = 0;
    }

    /**
     * Fixup method described on p. 339 of CLRS, 4e.
     */
    private void insertFixup(RBNode<K, V> z) {
        if(z == null) return;
        if(size == 1 || root==z){
            z.color = 0;
            return;
        }

        RBNode<K,V> y = null;
        while(z.getParent() != null){
            if(z.getParent().color != 1) break;

            if(z.getParent() == z.getParent().getParent().getLeft()){
                y = z.getParent().getParent().getRight();
                if(y != null && y.color == 1){
                    z.getParent().color = 0;
                    y.color = 0;
                    z.getParent().getParent().color = 1;
                    z = z.getParent().getParent();
                } else {
                    if(z == z.getParent().getRight()){
                        z = z.getParent();
                        leftRotate(z);
                    }
                    z.getParent().color = 0;
                    z.getParent().getParent().color = 1;
                    rightRotate(z.getParent().getParent());
                }
            } else {
                y = z.getParent().getParent().getLeft();
                if(y != null && y.color == 1){
                    z.getParent().color = 0;
                    y.color = 0;
                    z.getParent().getParent().color = 1;
                    z = z.getParent().getParent();
                } else {
                    if(z == z.getParent().getLeft()){
                        z = z.getParent();
                        rightRotate(z);
                    }
                    z.getParent().color = 0;
                    z.getParent().getParent().color = 1;
                    leftRotate(z.getParent().getParent());
                }
            }
        }
        ((RBNode<K,V>)root).color = 0;
    }

    /**
     * Left-rotate method described on p. 336 of CLRS, 4e.
     */
    private void leftRotate(Node<K, V> x) {
        if(x==null) return;
        if(size==1) return;
        Node<K,V> y = x.getRight();
        x.setRight(y.getLeft());
        if(y.getLeft() != null){
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == null){
            root = y;
        }else if(x == x.getParent().getLeft()){
            x.getParent().setLeft(y);
        }else{
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    /**
     * Right-rotate method described on p. 336 of CLRS, 4e.
     */
    private void rightRotate(Node<K, V> x) {
        if(x==null) return;
        if(size==1) return;
        Node<K,V> y = x.getLeft();
        x.setLeft(y.getRight());
        if(y.getRight() != null){
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == null){
            root = y;
        }else if(x == x.getParent().getLeft()){
            x.getParent().setLeft(y);
        }else{
            x.getParent().setRight(y);
        }
        y.setRight(x);
        x.setParent(y);
    }
}

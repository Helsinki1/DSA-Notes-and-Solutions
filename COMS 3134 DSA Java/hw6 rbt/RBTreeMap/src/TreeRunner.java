import java.lang.reflect.InvocationTargetException;

/**
 * Class for testing BSTreeMap, AVLTreeMap, and RBTreeMap in a generic manner.
 * @author Brian S. Borowski
 * @version 1.0.2 March 5, 2024
 */
public class TreeRunner<E extends Comparable<E>> {
    private static final String[] TREE_CL_ARG = { "bst", "avl", "rbt" };
    private static final String[] TREE_CLASSNAME =
            { "BSTreeMap", "AVLTreeMap", "RBTreeMap" };
    private final int classIndex;

    public TreeRunner(int classIndex) {
        this.classIndex = classIndex;
    }

    public void run(E[] array) throws ClassNotFoundException,
                                      NoSuchMethodException,
                                      InstantiationException,
                                      IllegalArgumentException,
                                      InvocationTargetException,
                                      IllegalAccessException {
        @SuppressWarnings("unchecked")
        Pair<E, E>[] pairs = new Pair[array.length];
        for (int i = 0; i < array.length; i++) {
            pairs[i] = new Pair<>(array[i], array[i]);
        }

        Class<?> cl = Class.forName(TREE_CLASSNAME[classIndex]);
        @SuppressWarnings("unchecked")
        BSTreeMap<E, E> treeMap =
            (BSTreeMap<E, E>)
            cl.getDeclaredConstructor(Pair[].class).newInstance((Object)pairs);
        System.out.println(treeMap.toAsciiDrawing());
        System.out.println();
        System.out.println("Height:                   " + treeMap.height());
        System.out.println("Total nodes:              " + treeMap.size());
        System.out.printf("Successful search cost:   %.3f\n",
                treeMap.successfulSearchCost());
        System.out.printf("Unsuccessful search cost: %.3f\n",
                treeMap.unsuccessfulSearchCost());
        treeMap.printTraversal(BSTreeMap.PREORDER);
        treeMap.printTraversal(BSTreeMap.INORDER);
        treeMap.printTraversal(BSTreeMap.POSTORDER);
        for (E element: array) {
            System.out.println("\nTree after deleting key " + element + ":");
            treeMap.remove(element);
            System.out.println(treeMap.toAsciiDrawing());
        }
    }

    /**
     * Main method to facilitate testing your code.
     * The first argument must be the string bst, avl, or rbt.
     * If the second command line argument parses to an int, the map will be of
     * type <Integer, Integer>, else it will be of type <String, String>.
     * @param args tree type followed by the values to insert into the tree
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println(
                "Usage: java TreeRunner <bst|avl|rbt> key1, key2, ..., keyn");
            System.exit(1);
        }
        int index = -1;
        for (int i = 0; i < TREE_CL_ARG.length; i++) {
            if (args[0].equalsIgnoreCase(TREE_CL_ARG[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.err.println(
                "Error: Invalid tree type '" + args[0] + "' received.");
            System.exit(1);
        }

        boolean usingInts = true;
        if (args.length > 1) {
            try {
                Integer.parseInt(args[1]);
            } catch (NumberFormatException nfe) {
                usingInts = false;
            }
        }

        if (usingInts) {
            Integer[] array = new Integer[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                try {
                    array[i - 1] = Integer.parseInt(args[i]);
                } catch (NumberFormatException nfe) {
                    System.err.println("Error: Invalid integer '" + args[i]
                            + "' found at index " + i + ".");
                    System.exit(1);
                }
            }
            (new TreeRunner<Integer>(index)).run(array);
        } else {
            String[] array = new String[args.length - 1];
            System.arraycopy(args, 1, array, 0, args.length - 1);
            (new TreeRunner<String>(index)).run(array);
        }
    }
}

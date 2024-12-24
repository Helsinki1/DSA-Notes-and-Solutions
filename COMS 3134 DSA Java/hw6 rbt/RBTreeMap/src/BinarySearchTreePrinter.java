import java.util.ArrayList;
import java.util.List;

/**
 * Class that draws an ASCII representation of the binary tree.
 * Borrowed from the Internet ages ago (source unknown)!
 * Adapted to this project by Brian S. Borowski on October 19, 2022.
 * Last modified on February 17, 2024.
 */
public class BinarySearchTreePrinter<K, V> {
    private static final int GAP = 1;
    private List<Integer> leftProfile, rightProfile;
    private int printNext;
    private StringBuilder builder;

    public BinarySearchTreePrinter() {
        leftProfile = new ArrayList<Integer>();
        rightProfile = new ArrayList<Integer>();
        builder = new StringBuilder();
    }

    public String toString() {
        return builder.toString();
    }

    /**
     * Converts the given Node into a String containing an ASCII representation
     * of the tree.
     */
    void createAsciiTree(Node<K, V> node) {
        if (node == null) {
            builder.append("Root is null.");
            return;
        }
        AsciiNode root = buildAsciiTree(node);
        computeEdgeLengths(root);
        for (int i = 0; i < root.height; i++) {
            leftProfile.add(i, (int)Short.MAX_VALUE);
        }
        computeLeftProfile(root, 0, 0);
        int minX = 0;
        for (int i = 0; i < root.height; i++) {
            minX = Math.min(minX, leftProfile.get(i));
        }
        int lastLine = root.height - 1;
        for (int i = 0; i < root.height; i++) {
            printNext = 0;
            printLevel(root, -minX, i);
            if (i != lastLine) {
                builder.append("\n");
            }
        }
        root = null;
        leftProfile.clear();
        rightProfile.clear();
    }

    /**
     * Prints the given level of the given tree, assuming that the node has the
     * given x-coordinate.
     */
    void printLevel(AsciiNode node, int x, int level) {
        if (node == null) {
            return;
        }
        int i, isleft = node.parentDirection == -1 ? 1 : 0;
        if (level == 0) {
            for (i = 0; i < x - printNext - (node.label.length() - isleft) / 2; i++) {
                builder.append(" ");
            }
            printNext += i;
            builder.append(node.label);
            printNext += node.label.length();
        } else if (node.edgeLength >= level) {
            if (node.left != null) {
                for (i = 0; i < x - printNext - level; i++) {
                    builder.append(" ");
                }
                printNext += i;
                builder.append("/");
                printNext++;
            }
            if (node.right != null) {
                for (i = 0; i < x - printNext + level; i++) {
                    builder.append(" ");
                }
                printNext += i;
                builder.append("\\");
                printNext++;
            }
        } else {
            printLevel(node.left, x - node.edgeLength - 1,
                       level - node.edgeLength - 1);
            printLevel(node.right, x + node.edgeLength + 1,
                       level - node.edgeLength - 1);
        }
    }

    /**
     * Fills in the edgeLength and height fields of the specified node.
     */
    void computeEdgeLengths(AsciiNode node) {
        if (node == null) {
            return;
        }
        computeEdgeLengths(node.left);
        computeEdgeLengths(node.right);

        // First fill in the edge length of the node.
        if (node.right == null && node.left == null) {
            node.edgeLength = 0;
        } else {
            int minH;
            if (node.left != null) {
                for (int i = 0; i < node.left.height; i++) {
                    rightProfile.add(i, (int)Short.MIN_VALUE);
                }
                computeRightProfile(node.left, 0, 0);
                minH = node.left.height;
            } else {
                minH = 0;
            }
            if (node.right != null) {
                for (int i = 0; i < node.right.height; i++) {
                    leftProfile.add(i, (int)Short.MAX_VALUE);
                }
                computeLeftProfile(node.right, 0, 0);
                minH = Math.min(node.right.height, minH);
            } else {
                minH = 0;
            }
            int delta = 4;
            for (int i = 0; i < minH; i++) {
                delta = Math.max(delta,
                            GAP + 2 + rightProfile.get(i) - leftProfile.get(i));
            }

            // If the node has two children of height 1, we allow the two leaves
            // to be within 1 instead of 2.
            if ((node.left != null && node.left.height == 1 ||
                 node.right != null && node.right.height == 1) && delta > 4) {
                delta--;
            }

            node.edgeLength = (delta + 1) / 2 - 1;
        }

        // Now fill in the height of the node.
        int h = 1;
        if (node.left != null) {
            h = Math.max(node.left.height + node.edgeLength + 1, h);
        }
        if (node.right != null) {
            h = Math.max(node.right.height + node.edgeLength + 1, h);
        }
        node.height = h;
    }

    AsciiNode buildAsciiTreeRecursive(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        AsciiNode asciiNode = new AsciiNode();
        asciiNode.left = buildAsciiTreeRecursive(node.getLeft());
        asciiNode.right = buildAsciiTreeRecursive(node.getRight());
        if (asciiNode.left != null) {
            asciiNode.left.parentDirection = -1;
        }
        if (asciiNode.right != null) {
            asciiNode.right.parentDirection = 1;
        }
        asciiNode.label = node.toString();

        return asciiNode;
    }

    /**
     * Make a copy of the tree using the AsciiNodes.
     */
    AsciiNode buildAsciiTree(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        AsciiNode asciiNode = buildAsciiTreeRecursive(node);
        asciiNode.parentDirection = 0;
        return asciiNode;
    }

    /**
     * The following function fills in the left profile for the given tree. It
     * assumes that the center of the label of the root of this tree is located
     * at a position (x, y). It assumes that the edge length fields have been
     * computed for this tree.
     */
    void computeLeftProfile(AsciiNode node, int x, int y) {
        if (node == null) {
            return;
        }
        int isleft = node.parentDirection == -1 ? 1 : 0;
        leftProfile.set(y,
                Math.min(leftProfile.get(y),
                         x - (node.label.length() - isleft) / 2));
        if (node.left != null) {
            for (int i = 1; i <= node.edgeLength; i++) {
                leftProfile.set(y + i, Math.min(leftProfile.get(y + i), x - i));
            }
        }
        computeLeftProfile(node.left, x - node.edgeLength - 1, y
                + node.edgeLength + 1);
        computeLeftProfile(node.right, x + node.edgeLength + 1, y
                + node.edgeLength + 1);
    }

    void computeRightProfile(AsciiNode node, int x, int y) {
        if (node == null) {
            return;
        }
        int notleft = node.parentDirection != -1 ? 1 : 0;
        rightProfile.set(y,
                Math.max(rightProfile.get(y),
                         x + (node.label.length() - notleft) / 2));
        if (node.right != null) {
            for (int i = 1; i <= node.edgeLength; i++) {
                rightProfile.set(y + i, Math.max(rightProfile.get(y + i), x + i));
            }
        }
        computeRightProfile(node.left, x - node.edgeLength - 1, y
                + node.edgeLength + 1);
        computeRightProfile(node.right, x + node.edgeLength + 1, y
                + node.edgeLength + 1);
    }

    static class AsciiNode {
        AsciiNode left, right;

        // Length of the edge from this node to its children
        int edgeLength;
        int height;

        // -1 left, 0 root, 1 right
        int parentDirection;

        String label;
    };
}

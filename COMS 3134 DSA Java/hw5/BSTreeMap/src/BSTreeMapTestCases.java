/**
 * @author Brian S. Borowski
 * Test cases for Programming Assignment 5 - BSTreeMap
 * COMS W3134
 * Date created: 10/19/2022
 * Last modified: 02/24/2024
 */
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unchecked")
public class BSTreeMapTestCases {

    public static final double DELTA = 1e-7;

    private Method getTraversal(BSTreeMap map, String traversal) throws NoSuchMethodException {
        Method method = map.getClass().getDeclaredMethod(traversal, Node.class, StringBuilder.class, int.class);
        method.setAccessible(true);
        return method;
    }

    private Method getIterativeSearchMethod(BSTreeMap map) throws NoSuchMethodException {
        Method method = map.getClass().getDeclaredMethod("iterativeSearch", Comparable.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    public void testPut01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>();
        assertEquals("[]", map.inorder());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        Integer retVal = map.put(10, 10);
        assertNull(retVal);
        assertEquals("[<10, 10>]", map.inorder());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertNull(((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 10)).getParent());
        retVal = map.put(2, 2);
        assertNull(retVal);
        assertEquals(10, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 2)).getParent().key);
        retVal = map.put(2, 3);
        assertEquals(2, retVal);
        assertEquals(10, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 2)).getParent().key);
        retVal = map.put(5, 2);
        assertNull(retVal);
        assertEquals(2, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 5)).getParent().key);
        retVal = map.put(12, 13);
        assertNull(retVal);
        assertEquals(10, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 12)).getParent().key);
        assertEquals("[<2, 3>, <5, 2>, <10, 10>, <12, 13>]", map.inorder());
        assertEquals(4, map.size());
        assertFalse(map.isEmpty());
    }

    @Test
    public void testPreorder01() {
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>();
        assertEquals("[]", map.preorder());
        try {
            assertEquals(0, getTraversal(map, "preorder").invoke(map, map.root, new StringBuilder(), 0));
        } catch (Exception e) {
            fail("preorder failed: " + e.getMessage());
        }

        map.put(10, 10);
        assertEquals("[<10, 10>]", map.preorder());
        try {
            assertEquals(1, getTraversal(map, "preorder").invoke(map, map.root, new StringBuilder(), 0));
        } catch (Exception e) {
            fail("preorder failed: " + e.getMessage());
        }

        map.put(2, 3);
        map.put(2, 3);
        map.put(5, 2);
        map.put(12, 13);
        assertEquals("[<10, 10>, <2, 3>, <5, 2>, <12, 13>]", map.preorder());
        try {
            assertEquals(4, getTraversal(map, "preorder").invoke(map, map.root, new StringBuilder(), 0));
        } catch (Exception e) {
            fail("preorder failed: " + e.getMessage());
        }
    }

    @Test
    public void testPostorder01() {
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>();
        assertEquals("[]", map.postorder());
        try {
            assertEquals(0, getTraversal(map, "postorder").invoke(map, map.root, new StringBuilder(), 0));
        } catch (Exception e) {
            fail("postorder failed: " + e.getMessage());
        }

        map.put(10, 10);
        assertEquals("[<10, 10>]", map.postorder());
        try {
            assertEquals(1, getTraversal(map, "postorder").invoke(map, map.root, new StringBuilder(), 0));
        } catch (Exception e) {
            fail("postorder failed: " + e.getMessage());
        }

        map.put(2, 3);
        map.put(2, 3);
        map.put(5, 2);
        map.put(12, 13);
        assertEquals("[<5, 2>, <2, 3>, <12, 13>, <10, 10>]", map.postorder());
        try {
            assertEquals(4, getTraversal(map, "postorder").invoke(map, map.root, new StringBuilder(), 0));
        } catch (Exception e) {
            fail("postorder failed: " + e.getMessage());
        }
    }

    @Test
    public void testInorder01() {
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>();
        assertEquals("[]", map.inorder());
        try {
            assertEquals(0, getTraversal(map, "inorder").invoke(map, map.root, new StringBuilder(), 0));
        } catch (Exception e) {
            fail("preorder failed: " + e.getMessage());
        }

        map.put(10, 10);
        assertEquals("[<10, 10>]", map.inorder());
        try {
            assertEquals(1, getTraversal(map, "inorder").invoke(map, map.root, new StringBuilder(), 0));
        } catch (Exception e) {
            fail("preorder failed: " + e.getMessage());
        }

        map.put(2, 3);
        map.put(2, 3);
        map.put(5, 2);
        map.put(12, 13);
        assertEquals("[<2, 3>, <5, 2>, <10, 10>, <12, 13>]", map.inorder());
        try {
            assertEquals(4, getTraversal(map, "inorder").invoke(map, map.root, new StringBuilder(), 0));
        } catch (Exception e) {
            fail("preorder failed: " + e.getMessage());
        }
    }

    @Test
    public void testCreateBST01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Pair<Integer, Integer>[] pairs = new Pair[6];
        for (int i = 0; i < 6; i++) {
            pairs[i] = new Pair(i, i);
        }
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>(pairs, true);
        // Tree should be:
        //     2
        //    / \
        //   /   \
        //  0     4
        //   \   / \
        //    1 3   5
        // Check parent pointers.
        assertNull(((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 2)).getParent());
        assertEquals(2, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 0)).getParent().key);
        assertEquals(2, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 4)).getParent().key);
        assertEquals(0, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 1)).getParent().key);
        assertEquals(4, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 3)).getParent().key);
        assertEquals(4, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 5)).getParent().key);
    }

    @Test
    public void testCreateBST02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Pair<Integer, Integer>[] pairs = new Pair[10];
        for (int i = 0; i < 10; i++) {
            pairs[i] = new Pair(i, i);
        }
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>(pairs, true);
        assertEquals("[<4, 4>, <1, 1>, <0, 0>, <2, 2>, <3, 3>, <7, 7>, <5, 5>, <6, 6>, <8, 8>, <9, 9>]", map.preorder());
        assertEquals("[<0, 0>, <1, 1>, <2, 2>, <3, 3>, <4, 4>, <5, 5>, <6, 6>, <7, 7>, <8, 8>, <9, 9>]", map.inorder());
        assertEquals(1, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 0)).getParent().key);
        assertEquals(4, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 1)).getParent().key);
        assertEquals(1, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 2)).getParent().key);
        assertEquals(2, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 3)).getParent().key);
        assertNull(((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 4)).getParent());
        assertEquals(7, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 5)).getParent().key);
        assertEquals(5, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 6)).getParent().key);
        assertEquals(4, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 7)).getParent().key);
        assertEquals(7, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 8)).getParent().key);
        assertEquals(8, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 9)).getParent().key);
    }

    @Test
    public void testGet01() {
        Pair<Integer, Integer>[] pairs = new Pair[10];
        for (int i = 0; i < 10; i++) {
            pairs[i] = new Pair(i, i+1);
        }
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>(pairs, true);
        for (int i = 0; i < 10; i++) {
            assertEquals(i + 1, map.get(i));
        }
        assertNull(map.get(10));
        assertNull(map.get(-1));
    }

    @Test
    public void testRemove01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Pair<Integer, Integer>[] pairs = new Pair[10];
        for (int i = 0; i < 10; i++) {
            pairs[i] = new Pair(i, i+1);
        }
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>(pairs, true);
        map.remove(5);
        assertEquals("[<4, 5>, <1, 2>, <0, 1>, <2, 3>, <3, 4>, <7, 8>, <6, 7>, <8, 9>, <9, 10>]", map.preorder());
        assertEquals("[<0, 1>, <1, 2>, <2, 3>, <3, 4>, <4, 5>, <6, 7>, <7, 8>, <8, 9>, <9, 10>]", map.inorder());
        assertEquals(9, map.size());
        assertFalse(map.isEmpty());
        assertEquals(7, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 6)).getParent().key);

        map.remove(1);
        assertEquals("[<4, 5>, <2, 3>, <0, 1>, <3, 4>, <7, 8>, <6, 7>, <8, 9>, <9, 10>]", map.preorder());
        assertEquals("[<0, 1>, <2, 3>, <3, 4>, <4, 5>, <6, 7>, <7, 8>, <8, 9>, <9, 10>]", map.inorder());
        assertEquals(8, map.size());
        assertFalse(map.isEmpty());
        assertEquals(2, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 3)).getParent().key);

        map.remove(3);
        assertEquals("[<4, 5>, <2, 3>, <0, 1>, <7, 8>, <6, 7>, <8, 9>, <9, 10>]", map.preorder());
        assertEquals("[<0, 1>, <2, 3>, <4, 5>, <6, 7>, <7, 8>, <8, 9>, <9, 10>]", map.inorder());
        assertEquals(7, map.size());
        assertFalse(map.isEmpty());
        assertEquals(2, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 0)).getParent().key);

        map.remove(8);
        assertEquals("[<4, 5>, <2, 3>, <0, 1>, <7, 8>, <6, 7>, <9, 10>]", map.preorder());
        assertEquals("[<0, 1>, <2, 3>, <4, 5>, <6, 7>, <7, 8>, <9, 10>]", map.inorder());
        assertEquals(6, map.size());
        assertFalse(map.isEmpty());
        assertEquals(7, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 9)).getParent().key);

        map.remove(4);
        assertEquals("[<6, 7>, <2, 3>, <0, 1>, <7, 8>, <9, 10>]", map.preorder());
        assertEquals("[<0, 1>, <2, 3>, <6, 7>, <7, 8>, <9, 10>]", map.inorder());
        assertEquals(5, map.size());
        assertFalse(map.isEmpty());
        assertEquals(6, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 7)).getParent().key);
        assertNull(((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 6)).getParent());

        map.remove(2);
        assertEquals("[<6, 7>, <0, 1>, <7, 8>, <9, 10>]", map.preorder());
        assertEquals("[<0, 1>, <6, 7>, <7, 8>, <9, 10>]", map.inorder());
        assertEquals(4, map.size());
        assertFalse(map.isEmpty());
        assertEquals(6, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 0)).getParent().key);

        map.remove(7);
        assertEquals("[<6, 7>, <0, 1>, <9, 10>]", map.preorder());
        assertEquals("[<0, 1>, <6, 7>, <9, 10>]", map.inorder());
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        assertEquals(6, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 9)).getParent().key);

        map.remove(6);
        assertEquals("[<9, 10>, <0, 1>]", map.preorder());
        assertEquals("[<0, 1>, <9, 10>]", map.inorder());
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        assertEquals(9, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 0)).getParent().key);

        map.remove(0);
        assertEquals("[<9, 10>]", map.preorder());
        assertEquals("[<9, 10>]", map.inorder());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertNull(((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 9)).getParent());

        // Try to remove something not there.
        map.remove(0);
        assertEquals("[<9, 10>]", map.preorder());
        assertEquals("[<9, 10>]", map.inorder());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertNull(((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 9)).getParent());

        map.remove(9);
        assertEquals("[]", map.preorder());
        assertEquals("[]", map.inorder());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    public void testCosts01() {
        Pair<Integer, Integer>[] pairs = new Pair[6];
        for (int i = 0; i < 6; i++) {
            pairs[i] = new Pair(i, i);
        }
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>(pairs, true);
        assertEquals(2.333333333333333, map.successfulSearchCost(), DELTA);
        assertEquals(2.857142857142857, map.unsuccessfulSearchCost(), DELTA);
    }
}

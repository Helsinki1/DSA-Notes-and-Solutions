/**
 * @author Brian S. Borowski, Palash Sharma
 * Test cases for Programming Assignment 6 - RBTreeMap
 * COMS W3134
 * Date created: 03/01/2024
 * Last modified: 03/03/2024
 */

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unchecked")
public class RBTreeMapTestCases {

    public static final double DELTA = 1e-7;

    private Method getTraversal(RBTreeMap map, String traversal) throws NoSuchMethodException {
        Method method = map.getClass().getSuperclass().getDeclaredMethod(traversal, Node.class, StringBuilder.class, int.class);
        method.setAccessible(true);
        return method;
    }

    private Method getIterativeSearchMethod(RBTreeMap map) throws NoSuchMethodException {
        Method method = map.getClass().getSuperclass().getDeclaredMethod("iterativeSearch", Comparable.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    public void testPut01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RBTreeMap<Integer, Integer> map = new RBTreeMap<>();
        assertEquals("[]", map.inorder());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        Integer retVal = map.put(10, 10);
        assertNull(retVal);
        assertEquals("[<10, 10, B>]", map.inorder());
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
        System.out.println(map.toAsciiDrawing());
        assertNull(((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 5)).getParent());
        retVal = map.put(12, 13);
        assertNull(retVal);
        assertEquals(10, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 12)).getParent().key);
        assertEquals("[<2, 3, B>, <5, 2, B>, <10, 10, B>, <12, 13, R>]", map.inorder());
        assertEquals(4, map.size());
        assertFalse(map.isEmpty());
    }

    @Test
    public void testGet01() {
        Pair<Integer, Integer>[] pairs = new Pair[10];
        for (int i = 0; i < 10; i++) {
            pairs[i] = new Pair(i, i+1);
        }
        RBTreeMap<Integer, Integer> map = new RBTreeMap<>(pairs, true);
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
        RBTreeMap<Integer, Integer> map = new RBTreeMap<>(pairs, true);

        Integer retVal = map.remove(4);
        assertEquals(5, retVal);
        assertEquals("[<5, 6, B>, <1, 2, B>, <0, 1, B>, <2, 3, B>, <3, 4, R>, <7, 8, B>, <6, 7, B>, <8, 9, B>, <9, 10, R>]", map.preorder());
        assertEquals("[<0, 1, B>, <1, 2, B>, <2, 3, B>, <3, 4, R>, <5, 6, B>, <6, 7, B>, <7, 8, B>, <8, 9, B>, <9, 10, R>]", map.inorder());
        assertEquals(9, map.size());
        assertFalse(map.isEmpty());
        assertEquals(5, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 1)).getParent().key);

        retVal = map.remove(0);
        assertEquals(1, retVal);
        assertEquals("[<5, 6, B>, <2, 3, B>, <1, 2, B>, <3, 4, B>, <7, 8, B>, <6, 7, B>, <8, 9, B>, <9, 10, R>]", map.preorder());
        assertEquals("[<1, 2, B>, <2, 3, B>, <3, 4, B>, <5, 6, B>, <6, 7, B>, <7, 8, B>, <8, 9, B>, <9, 10, R>]", map.inorder());
        assertEquals(8, map.size());
        assertFalse(map.isEmpty());
        assertEquals(2, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 1)).getParent().key);

        retVal = map.remove(5);
        assertEquals(6, retVal);
        assertEquals("[<6, 7, B>, <2, 3, B>, <1, 2, B>, <3, 4, B>, <8, 9, B>, <7, 8, B>, <9, 10, B>]", map.preorder());
        assertEquals("[<1, 2, B>, <2, 3, B>, <3, 4, B>, <6, 7, B>, <7, 8, B>, <8, 9, B>, <9, 10, B>]", map.inorder());
        assertEquals(7, map.size());
        assertFalse(map.isEmpty());
        assertEquals(8, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 7)).getParent().key);

        retVal = map.remove(7);
        assertEquals(8, retVal);
        assertEquals("[<6, 7, B>, <2, 3, R>, <1, 2, B>, <3, 4, B>, <8, 9, B>, <9, 10, R>]", map.preorder());
        assertEquals("[<1, 2, B>, <2, 3, R>, <3, 4, B>, <6, 7, B>, <8, 9, B>, <9, 10, R>]", map.inorder());
        assertEquals(6, map.size());
        assertFalse(map.isEmpty());
        assertEquals(6, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 8)).getParent().key);

        retVal = map.remove(8);
        assertEquals(9, retVal);
        assertEquals("[<6, 7, B>, <2, 3, R>, <1, 2, B>, <3, 4, B>, <9, 10, B>]", map.preorder());
        assertEquals("[<1, 2, B>, <2, 3, R>, <3, 4, B>, <6, 7, B>, <9, 10, B>]", map.inorder());
        assertEquals(5, map.size());
        assertFalse(map.isEmpty());
        assertEquals(6, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 9)).getParent().key);

        retVal = map.remove(9);
        assertEquals(10, retVal);
        assertEquals("[<2, 3, B>, <1, 2, B>, <6, 7, B>, <3, 4, R>]", map.preorder());
        assertEquals("[<1, 2, B>, <2, 3, B>, <3, 4, R>, <6, 7, B>]", map.inorder());
        assertEquals(4, map.size());
        assertFalse(map.isEmpty());
        assertEquals(6, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 3)).getParent().key);

        retVal = map.remove(1);
        assertEquals(2, retVal);
        assertEquals("[<3, 4, B>, <2, 3, B>, <6, 7, B>]", map.preorder());
        assertEquals("[<2, 3, B>, <3, 4, B>, <6, 7, B>]", map.inorder());
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        assertEquals(3, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 2)).getParent().key);

        retVal = map.remove(3);
        assertEquals(4, retVal);
        assertEquals("[<6, 7, B>, <2, 3, R>]", map.preorder());
        assertEquals("[<2, 3, R>, <6, 7, B>]", map.inorder());
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        assertEquals(6, ((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 2)).getParent().key);

        retVal = map.remove(6);
        assertEquals(7, retVal);
        assertEquals("[<2, 3, B>]", map.preorder());
        assertEquals("[<2, 3, B>]", map.inorder());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertNull(((Node<Integer, Integer>)getIterativeSearchMethod(map).invoke(map, 2)).getParent());

        retVal = map.remove(2);
        assertEquals(3, retVal);
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
        RBTreeMap<Integer, Integer> map = new RBTreeMap<>(pairs, true);
        assertEquals(2.333333333333333, map.successfulSearchCost(), DELTA);
        assertEquals(2.857142857142857, map.unsuccessfulSearchCost(), DELTA);
    }
}

/**
 * @author Brian S. Borowski
 * Test cases for Programming Assignment 3 - List methods
 * COMS W3134
 * Last modified: 09/30/2023
 */
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ListTestCases {

    @Test
    public void testToString01() {
        MyList<String> arrayList = new MyArrayList<>();
        assertEquals("[]", arrayList.toString());
    }

    @Test
    public void testToString02() {
        MyList<String> arrayList = new MyArrayList<>();
        arrayList.add("A");
        assertEquals("[A]", arrayList.toString());
    }

    @Test
    public void testToString03() {
        MyList<String> arrayList = new MyArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        assertEquals("[A, B]", arrayList.toString());
    }

    @Test
    public void testToString04() {
        MyList<String> arrayList = new MyArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("E");
        assertEquals("[A, B, C, D, E]", arrayList.toString());
    }

    @Test
    public void testToString05() {
        MyList<String> linkedList = new MyLinkedList<>();
        assertEquals("[]", linkedList.toString());
    }

    @Test
    public void testToString06() {
        MyList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        assertEquals("[A]", linkedList.toString());
    }

    @Test
    public void testToString07() {
        MyList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        assertEquals("[A, B]", linkedList.toString());
    }

    @Test
    public void testToString08() {
        MyList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        linkedList.add("E");
        assertEquals("[A, B, C, D, E]", linkedList.toString());
    }

    @Test
    public void testAdd01() {
        MyList<String> arrayList = new MyArrayList<>();
        try {
            arrayList.add(-1, "apples");
            fail("Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            assertEquals("Index: -1, list size: 0", ioobe.getMessage());
        }
        arrayList.add("apples");
        try {
            arrayList.add(2, "bananas");
            fail("Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            assertEquals("Index: 2, list size: 1", ioobe.getMessage());
        }
    }

    @Test
    public void testAdd02() {
        MyList<String> arrayList = new MyArrayList<>();
        try {
            arrayList.add(0, "apples");
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[apples]", arrayList.toString());
        assertEquals(1, arrayList.size());
    }

    @Test
    public void testAdd03() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        try {
            arrayList.add(0, "A");
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[A, apples, bananas, cantaloupes, dates]", arrayList.toString());
        assertEquals(5, arrayList.size());
    }

    @Test
    public void testAdd04() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        try {
            arrayList.add(2, "C");
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[apples, bananas, C, cantaloupes, dates]", arrayList.toString());
        assertEquals(5, arrayList.size());
    }

    @Test
    public void testAdd05() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        try {
            arrayList.add(4, "E");
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[apples, bananas, cantaloupes, dates, E]", arrayList.toString());
        assertEquals(5, arrayList.size());
    }

    @Test
    public void testAdd06() {
        MyList<String> linkedList = new MyLinkedList<>();
        try {
            linkedList.add(-1, "apples");
            fail("Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            assertEquals("Index: -1, list size: 0", ioobe.getMessage());
        }
        linkedList.add("apples");
        try {
            linkedList.add(2, "bananas");
            fail("Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            assertEquals("Index: 2, list size: 1", ioobe.getMessage());
        }
    }

    @Test
    public void testAdd07() {
        MyList<String> linkedList = new MyLinkedList<>();
        try {
            linkedList.add(0, "apples");
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[apples]", linkedList.toString());
        assertEquals(1, linkedList.size());
    }

    @Test
    public void testAdd08() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        try {
            linkedList.add(0, "A");
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[A, apples, bananas, cantaloupes, dates]", linkedList.toString());
        assertEquals(5, linkedList.size());
    }

    @Test
    public void testAdd09() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        try {
            linkedList.add(2, "C");
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[apples, bananas, C, cantaloupes, dates]", linkedList.toString());
        assertEquals(5, linkedList.size());
    }

    @Test
    public void testAdd10() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        try {
            linkedList.add(4, "E");
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[apples, bananas, cantaloupes, dates, E]", linkedList.toString());
        assertEquals(5, linkedList.size());
    }

    @Test
    public void testRemove01() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        try {
            arrayList.remove(-1);
            fail("Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            assertEquals("Index: -1, list size: 4", ioobe.getMessage());
        }
    }

    @Test
    public void testRemove02() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        try {
            arrayList.remove(3);
            fail("Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            assertEquals("Index: 3, list size: 3", ioobe.getMessage());
        }
    }

    @Test
    public void testRemove03() {
        String[] values = new String[] {"apples"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        try {
            arrayList.remove(0);
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[]", arrayList.toString());
        assertEquals(0, arrayList.size());
    }

    @Test
    public void testRemove04() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        try {
            arrayList.remove(3);
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[apples, bananas, cantaloupes]", arrayList.toString());
        assertEquals(3, arrayList.size());
    }

    @Test
    public void testRemove05() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        try {
            arrayList.remove(0);
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[bananas, cantaloupes, dates]", arrayList.toString());
        assertEquals(3, arrayList.size());
    }

    @Test
    public void testRemove06() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        try {
            linkedList.remove(-1);
            fail("Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            assertEquals("Index: -1, list size: 4", ioobe.getMessage());
        }
    }

    @Test
    public void testRemove07() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        try {
            linkedList.remove(3);
            fail("Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            assertEquals("Index: 3, list size: 3", ioobe.getMessage());
        }
    }

    @Test
    public void testRemove08() {
        String[] values = new String[] {"apples"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        try {
            linkedList.remove(0);
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[]", linkedList.toString());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void testRemove09() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        try {
            linkedList.remove(3);
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[apples, bananas, cantaloupes]", linkedList.toString());
        assertEquals(3, linkedList.size());
    }

    @Test
    public void testRemove10() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        try {
            linkedList.remove(0);
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Exception should not have been thrown.");
        }
        assertEquals("[bananas, cantaloupes, dates]", linkedList.toString());
        assertEquals(3, linkedList.size());
    }

    @Test
    public void testIndexOf01() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        int index = 0;
        for (String v : values) {
            assertEquals(index++, arrayList.indexOf(v));
        }
    }

    @Test
    public void testIndexOf02() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        assertEquals(-1, arrayList.indexOf("oranges"));
    }

    @Test
    public void testIndexOf03() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        int index = 0;
        for (String v : values) {
            assertEquals(index++, linkedList.indexOf(v));
        }
    }

    @Test
    public void testIndexOf04() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        assertEquals(-1, linkedList.indexOf("oranges"));
    }

    @Test
    public void testIndexesOf01() {
        MyList<String> arrayList = new MyArrayList<>();
        int[] indexes = arrayList.indexesOf("apples");
        assertEquals(0, indexes.length);
    }

    @Test
    public void testIndexesOf02() {
        String[] values = new String[] {"apples", "bananas", "apples", "cantaloupes", "dates", "apples"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        int[] indexes = arrayList.indexesOf("apples");
        assertEquals(3, indexes.length);
        assertEquals("[0, 2, 5]", Arrays.toString(indexes));
    }

    @Test
    public void testIndexesOf03() {
        String[] values = new String[] {"apples", "bananas", "apples", "cantaloupes", "dates", "apples"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        int[] indexes = arrayList.indexesOf("apple");
        assertEquals(0, indexes.length);
        assertEquals("[]", Arrays.toString(indexes));
    }

    @Test
    public void testIndexesOf04() {
        MyList<String> linkedList = new MyLinkedList<>();
        int[] indexes = linkedList.indexesOf("apples");
        assertEquals(0, indexes.length);
    }

    @Test
    public void testIndexesOf05() {
        String[] values = new String[] {"apples", "bananas", "apples", "cantaloupes", "dates", "apples"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        int[] indexes = linkedList.indexesOf("apples");
        assertEquals(3, indexes.length);
        assertEquals("[0, 2, 5]", Arrays.toString(indexes));
    }

    @Test
    public void testIndexesOf06() {
        String[] values = new String[] {"apples", "bananas", "apples", "cantaloupes", "dates", "apples"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        int[] indexes = linkedList.indexesOf("apple");
        assertEquals(0, indexes.length);
        assertEquals("[]", Arrays.toString(indexes));
    }

    @Test
    public void testReverse01() {
        MyList<String> arrayList = new MyArrayList<>();
        arrayList.reverse();
        assertEquals("[]", arrayList.toString());
    }

    @Test
    public void testReverse02() {
        String[] values = new String[] {"apples"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v: values) {
            arrayList.add(v);
        }
        arrayList.reverse();
        Iterator<String> arrayListIter = arrayList.iterator();
        int index = values.length - 1;
        while (arrayListIter.hasNext()) {
            assertEquals(values[index], arrayListIter.next());
        }
    }

    @Test
    public void testReverse03() {
        String[] values = new String[] {"apples", "bananas"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        arrayList.reverse();
        Iterator<String> arrayListIter = arrayList.iterator();
        int index = values.length - 1;
        while (arrayListIter.hasNext()) {
            assertEquals(values[index--], arrayListIter.next());
        }
    }

    @Test
    public void testReverse04() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        arrayList.reverse();
        Iterator<String> arrayListIter = arrayList.iterator();
        int index = values.length - 1;
        while (arrayListIter.hasNext()) {
            assertEquals(values[index--], arrayListIter.next());
        }
    }

    @Test
    public void testReverse05() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> arrayList = new MyArrayList<>();
        for (String v : values) {
            arrayList.add(v);
        }
        arrayList.reverse();
        Iterator<String> arrayListIter = arrayList.iterator();
        int index = values.length - 1;
        while (arrayListIter.hasNext()) {
            assertEquals(values[index--], arrayListIter.next());
        }
    }

    @Test
    public void testReverse06() {
        MyList<String> linkedList = new MyLinkedList<>();
        linkedList.reverse();
        assertEquals("[]", linkedList.toString());
    }

    @Test
    public void testReverse07() {
        String[] values = new String[] {"apples"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v: values) {
            linkedList.add(v);
        }
        linkedList.reverse();
        Iterator<String> linkedListIter = linkedList.iterator();
        int index = values.length - 1;
        while (linkedListIter.hasNext()) {
            assertEquals(values[index], linkedListIter.next());
        }
    }

    @Test
    public void testReverse08() {
        String[] values = new String[] {"apples", "bananas"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        linkedList.reverse();
        Iterator<String> linkedListIter = linkedList.iterator();
        int index = values.length - 1;
        while (linkedListIter.hasNext()) {
            assertEquals(values[index--], linkedListIter.next());
        }
    }

    @Test
    public void testReverse09() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        linkedList.reverse();
        Iterator<String> linkedListIter = linkedList.iterator();
        int index = values.length - 1;
        while (linkedListIter.hasNext()) {
            assertEquals(values[index--], linkedListIter.next());
        }
    }

    @Test
    public void testReverse10() {
        String[] values = new String[] {"apples", "bananas", "cantaloupes", "dates"};
        MyList<String> linkedList = new MyLinkedList<>();
        for (String v : values) {
            linkedList.add(v);
        }
        linkedList.reverse();
        Iterator<String> linkedListIter = linkedList.iterator();
        int index = values.length - 1;
        while (linkedListIter.hasNext()) {
            assertEquals(values[index--], linkedListIter.next());
        }
    }
}

import java.util.Iterator;

/**
 * Resizable-array implementation of the MyList interface.
 * @author David (Yiming) Xiong
 * @version 1.1.3 October 3, 2024
 */
public class MyArrayList<E> implements MyList<E>, MyStack<E> {
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    Object[] elementData; // non-private to simplify nested class access

    /**
     * Constructs an empty list with the specified initial capacity.
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element  element to be appended to this list
     * @return true
     */
    public boolean add(E element) {
        if (size >= elementData.length) {
            Object[] newData = new Object[size * 2 + 1];
            for (int i = 0; i < size; i++) {
                newData[i] = elementData[i];
            }
            elementData = newData;
        }
        elementData[size++] = element;
        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index  index of the element to return
     * @return       the element at the specified position in this list
     * @throws       IndexOutOfBoundsException - if the index is out of range
     *               (index < 0 || index >= size())
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        return (E)elementData[index];
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index    index of the element to return
     * @param element  element to be stored at the specified position
     * @return  the element at the specified position in this list
     * @throws  IndexOutOfBoundsException - if the index is out of range
     *          (index < 0 || index >= size())
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        @SuppressWarnings("unchecked")
        E oldValue = (E)elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        // clear to let GC do its work
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /**
     * Returns an iterator over the elements in this list (in proper
     * sequence).
     * The returned list iterator is fail-fast -- modification of the elements
     * is not permitted during iteration.
     */
    public Iterator<E> iterator() {
        return new ListItr();
    }

    /**
     * Returns a string representation of the list. The string will begin with
     * a '[' and end with a ']'. Inside the square brackets will be a comma-
     * separated list of values, such as [Brian, Susan, Jamie].
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        if (size > 0) {
            builder.append(elementData[0].toString());
        }
        for (int i = 1; i < size; i++) {
            builder.append(", ").append(elementData[i].toString());
        }
        builder.append(']');
        return builder.toString();
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     * @param index    index at which the specified element is to be inserted
     * @param element  element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index > size())
     * The exception message must be:
     * "Index: " + index + ", list size: " + size
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        if (size >= elementData.length) {
            Object[] newData = new Object[size * 2 + 1];
            for (int i = 0; i < index; i++) {
                newData[i] = elementData[i];
            }
            for (int i = index; i < size; i++) {
                newData[i + 1] = elementData[i];
            }
            elementData = newData;
        } else {
            for (int i = size - 1; i >= index; i--) {
                elementData[i + 1] = elementData[i];
            }
        }
        elementData[index] = element;
        size++;
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index  the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     * The exception message must be:
     * "Index: " + index + ", list size: " + size
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        @SuppressWarnings("unchecked")
        E oldValue = (E)elementData[index];
        --size;
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size] = null;
        return oldValue;
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element. More
     * formally, returns the lowest index i such that Objects.equals(o, get(i)),
     * or -1 if there is no such index.
     * @param element element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns an array of indexes of each occurrence of the specified element
     * in this list, in ascending order. If the specified element is not found,
     * a non-null empty array (not null) is returned.
     * @param element element to search for
     * @return an array of each occurrence of the specified element in this
     * list
     */
    public int[] indexesOf(E element) {
        MyList<Integer> indexes = new MyArrayList<>();
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                indexes.add(i);
            }
        }
        int indexesSize = indexes.size();
        int[] indexesArray = new int[indexesSize];
        for (int i = 0; i < indexesSize; i++) {
            indexesArray[i] = indexes.get(i);
        }
        return indexesArray;
    }

    /**
     * Reverses the data in the list.
     * For MyArrayList, the data inside the underlying array is moved. For
     * MyLinkedList, the tail must become the head, and all the pointers are
     * reversed. Both implementations must run in Theta(n).
     */
    public void reverse() {
        for (int i = 0, mid = size / 2, opposite = size - 1; i < mid;
             i++, opposite--) {
            Object temp = elementData[i];
            elementData[i] = elementData[opposite];
            elementData[opposite] = temp;
        }
    }

    private class ListItr implements Iterator<E> {
        private int current;

        ListItr() {
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            return (E)elementData[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // public boolean isEmpty() {
    //     return size == 0;
    // }
    // public int size() {
    //     return size;
    // }
    // public void clear() {
    //     // clear to let GC do its work
    //     for (int i = 0; i < size; i++) {
    //         elementData[i] = null;
    //     }
    //     size = 0;
    // }
    public void push(E item) {
        if (size >= elementData.length) {
            Object[] newData = new Object[size * 2 + 1];
            for (int i = 0; i < size; i++) {
                newData[i] = elementData[i];
            }
            elementData = newData;
        }
        elementData[size] = item;
        size++;
    }

    public E pop() throws StackException {
        if(isEmpty()) throw new StackException("Attempt to pop from empty stack.");
        else {
            E output = (E)elementData[size-1];
            elementData[size-1] = null;
            size--;
            return output;
        }
    }

    public E peek() throws StackException {
        if(isEmpty()) throw new StackException("Attempt to peek at empty stack.");
        else {
            E output = (E)elementData[size-1];
            return output;
        }
    }

}

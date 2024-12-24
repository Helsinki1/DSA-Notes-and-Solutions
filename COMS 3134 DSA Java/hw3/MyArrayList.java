import java.util.Iterator;

/**
 * Resizable-array implementation of the MyList interface.
 * @author David (Yiming) Xiong
 * @version 1.0.1 September 23, 2024
 */
public class MyArrayList<E> implements MyList<E> {
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
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        E oldValue = (E)elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * Removes all the elements from this list.
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




    // MY WORK

    public String toString() {
        String output = "[";
        String endChar;
        if(size == 0) return "[[]]";
        for(int i=0; i<size; i++){
            endChar = (i==size-1)? "]" : ", ";
            output += elementData[i].toString() + endChar;
        }
        return output;
    }

    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        if (size >= elementData.length) {
            Object[] newData = new Object[size * 2 + 1];
            for (int i = 0; i < size; i++) {
                newData[i] = elementData[i];
            }
            elementData = newData;
        }
        for(int i=size-1; i>=index; i++){
            elementData[i+1] = elementData[i];
        }
        elementData[index] = element;

        size++;
    }

    public E remove(int index) {
        E output = (E)elementData[index];
        for(int i=size-1; i>index; i++){
            elementData[i-1] = elementData[i];
        }
        elementData[size-1] = null;
        size--;
        return output;
    }

    public int indexOf(E element) {
        for(int i=0; i<size; i++){
            if(elementData[i] == element){
                return i;
            }
        }
        return -1;
    }

    public int[] indexesOf(E element) {
        int count = 0;
        for(int i=0; i<size; i++){
            if(elementData[i] == element){
                count++;
            }
        }
        int[] output = new int[count];
        int ind = 0;
        for(int i=0; i<size; i++){
            if(elementData[i] == element){
                output[ind] = i;
                ind++;
            }
        }
        return output;
    }

    public void reverse() {
        int pointer1 = 0;
        int pointer2 = size-1;
        Object mover1;
        Object mover2;

        while(pointer2 - pointer1 > 0){
            mover1 = elementData[pointer1];
            mover2 = elementData[pointer2];
            elementData[pointer1] = mover2;
            elementData[pointer2] = mover1;
            pointer1++;
            pointer2--;
        }
    }
}


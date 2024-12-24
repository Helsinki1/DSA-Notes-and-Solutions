import java.util.Iterator;

/**
 * Linked list implementation of the MyList interface.
 * @author David (Yiming) Xiong
 * @version 1.0.1 September 23, 2024
 */
public class MyLinkedList<E> implements MyList<E> {
    private Node head, tail;
    private int size;

    /**
     * Constructs an empty list.
     */
    public MyLinkedList() {
        head = tail = null;
        size = 0;
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
        Node p = head;
        for (int i = 0; i < index; i++, p = p.next);
        E oldElement = p.element;
        p.element = element;
        return oldElement;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index  index of the element to return
     * @return       the element at the specified position in this list
     * @throws       IndexOutOfBoundsException - if the index is out of range
     *               (index < 0 || index >= size())
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        Node p = head;
        for (int i = 0; i < index; i++, p = p.next);
        return p.element;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element  element to be appended to this list
     * @return true
     */
    public boolean add(E element) {
        Node n = new Node(element);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
        return true;
    }

    /**
     * Removes all the elements from this list.
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    public Iterator<E> iterator() {
        return new ListItr();
    }

    private class ListItr implements Iterator<E> {
        private Node current;

        ListItr() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E element = current.element;
            current = current.next;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Node next;
        E element;

        public Node(E element) {
            this.element = element;
        }
    }


    // MY WORK

    public String toString() {
        String output = "[";
        String lastChar;
        Node current = head;
        if(size == 0) return "[]";
        while(current != null){
            lastChar = (current==tail)? "]" : ", ";
            output += current.element + lastChar;
            current = current.next;
        }
        return output;
    }

    public void add(int index, E element) {
        if (index == 0 && size == 0) {
            Node p = new Node(element);
            head = p;
            tail = p;
        } else if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException(
                        "Index: " + index + ", list size: " + size);
        } else if (index == 0) {
            Node p = new Node(element);
            p.next = head;
            head = p;
        } else if (index == size) {
            Node p = new Node(element);
            tail.next = p;
            tail = p;
        } else {
            Node insertion = new Node(element);
            Node p = head;
            for(int i=0; i<index-1; i++) p = p.next;
            Node prev = p.next;
            p.next = insertion;
            insertion.next = prev;
        }
        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        } else if (index == 0) {
            Node deletion = head;
            head = head.next;
            size--;
            return (E)deletion.element;
        } else if (index == size-1) {
            Node p = head;
            for(int i=0; i<index-1; i++) p = p.next;
            Node deletion = p.next;
            tail = p;
            tail.next = null;
            size--;
            return (E)deletion.element;
        } else {
            Node p = head;
            for(int i=0; i<index-1; i++) p = p.next;
            Node deletion = p.next;
            p.next = p.next.next;
            size--;
            return (E) deletion.element;
        }
    }

    public int indexOf(E element) {
        Node p = head;
        int ind = 0;
        while(p != null){
            if(p.element == element) return ind;
            p = p.next;
            ind++;
        }
        return -1;
    }

    public int[] indexesOf(E element) {
        Node p = head;
        int ind = 0;
        int count = 0;
        while(p != null){
            if(p.element == element) count++;
            p = p.next;
            ind++;
        }
        int[] output = new int[count];
        p = head;
        ind = 0;
        int outInd = 0;
        while(p != null){
            if(p.element == element){
                output[outInd] = ind;
                outInd++;
            }
            p = p.next;
            ind++;
        }
        return output;
    }

    public void reverse() {
        if(size <= 1) {
            return;
        }
        
        Node temp = null;
        Node current = head;
        Node prev = null;

        while(current != null){
            temp = current.next;
            current.next = prev;
            current = temp;
            prev = current;
        }
        head = prev;
        
        tail.next = null;
    }

}


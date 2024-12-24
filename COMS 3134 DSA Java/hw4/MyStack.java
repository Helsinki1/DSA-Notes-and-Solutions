import java.io.*;

public interface MyStack<E> {

    /**
     * Tests if this stack is empty.
     * @return true if and only if this stack contains no items;
     * false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this stack.
     * @return the number of elements in this stack
     */
    int size();

    /**
     * Removes all the elements from this stack.
     */
    void clear();

    /**
     * Pushes an item onto the top of this stack. This has exactly the same
     * effect as: add(item)
     * @param item  the item to be pushed onto this stack
     */
    void push(E item);

    /**
     * Removes the object at the top of this stack and returns that object.
     * @return the object at the top of this stack (the last item in the
     * MyArrayList).
     * @throws StackException if the stack is empty. The exception's message
     * must be "Attempt to pop from empty stack."
     */
    E pop() throws StackException;

    /**
     * Looks at the object at the top of this stack without removing it from the
     * stack.
     * @return the object at the top of this stack (the last item in the
     * MyArrayList).
     * @throws StackException if the stack is empty. The exception's message
     * must be "Attempt to peek at empty stack."
     */
    E peek() throws StackException;
}

class StackException extends Exception {
    //@Serial
    private static final long serialVersionUID = 1L;

    public StackException(final String msg) {
        super(msg);
    }

    public StackException(final String msg, final Throwable t) {
        super(msg, t);
    }
}

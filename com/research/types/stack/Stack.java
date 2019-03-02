package com.research.types.stack;

/**
 * 
 * @author cgordon
 * @version 1.0
 *  
 * A small stack interface.  You can query the size of the stack and
 * ask whether it is empty, push items, pop items, and peek at the top
 * item.
 */
public interface Stack {

    /**
     * Adds the given item to the top of the stack.
     */
    void push(Object item);

    /**
     * Removes the top item from the stack and returns it.
     * @exception java.util.NoSuchElementException if the stack is empty.
     */
    Object pop();

    /**
     * Returns the top item from the stack without popping it.
     * @exception java.util.NoSuchElementException if the stack is empty.
     */
    Object peek();

    /**
     * Returns the number of items currently in the stack.
     */
    int size();

    /**
     * Returns whether the stack is empty or not.
     */
    boolean isEmpty();
} 
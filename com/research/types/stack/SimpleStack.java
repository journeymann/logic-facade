/**
 * 
 */
package com.research.types.stack;

import java.util.LinkedList;

/**
 * 
 * @author cgordon
 * @version 1.0
 * A stack class implemented as a wrapper around a java.util.LinkedList.
 * All stack methods simply delegate to LinkedList methods.
 */
public class SimpleStack implements Stack {
	
	private final int MAX = 10;

    private LinkedList<Object> list = new LinkedList<Object>();

    public void push(Object item) { if(list.size() < MAX) { list.addFirst(item);}}
    public Object pop() {return list.removeFirst();}
    public Object peek() {return list.getFirst();}
    public int size() {return list.size();}
    public boolean isEmpty() {return list.isEmpty();}
    
}
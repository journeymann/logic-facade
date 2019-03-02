/**
 * 
 */
package com.research.types.queue;

import com.research.types.common.Data;

/**
 * @author cgordon
 * 
 * Node definition for user defined linked list 
 *
 */
public class Node{
	// Fields
	Data data;
	Node next;

	/** Inner class constructor.
	 * 
	 * @param <code>Data</code> input data information   
	 */
	public Node(Data data) { this.data = data; next = null; }

	/** User defined toString method that effectively overrides the default Object toString method. 
	 */
	@Override
	public String toString() { return String.valueOf(data); }
}

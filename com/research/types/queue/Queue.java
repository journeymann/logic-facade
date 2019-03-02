/**
 * 
 */
package com.research.types.queue;

import com.research.types.common.Data;

/**
 * @author cgordon
 * @version 1.0
 * 
 * An Generic queue ADT with a sample Data class member data
 * 
 */

class Queue{

	/** Fields */
	private Node front;
	private Node back;
	private int length;
	private String label;

	/** Queue constructor (no param) 
	 */
	public Queue() { front = back = null; length = 0; }
	
	/** Queue constructor which accepts a queue label for identifying the queue in a multi-queue environment 
	 * 
	 * @param label to identify the queue <code>String</code>
	 */
	public Queue(String label_) { front = back = null; length = 0; label = label_;}

	/** front queue operation that returns front element in this queue or a 
	 * reference to the queue object / data structure..
	 * 
	 * @return <code>Data</code> data type member class front of the queue.
	 */
	public Data front(){
		if( this.isEmpty() ){
			throw new RuntimeException("front() called on empty Queue");
		}
		return front.data;
	}

	/** Standard length  operation returns length of this queue
	 * 
	 * @return the length of the queue ADT
	 */
	public int length() { return length; }

	/** isEmpty operation returns true if this is an empty queue, false otherwise
	 * 
	 * @return boolean flag indicating queue is empty
	 */
	public boolean isEmpty() { return length == 0; }

	/** Enqueue operation appends data to back of this queue
	 * 
	 * @param <code>Data</code> data type member class
	 */
	public void Enqueue(Data data){
		
		Node node = new Node(data);
		
		if( this.isEmpty() ) { 
			front = back = node; 
		}else { 
			back.next = node; back = node; 
		}
		
		System.out.printf("Added: %s", data);
		
		length++;
	}

	/** Dequeue operation removes the element from front of this queue
	 * 
	 * @return <code>Data</code> object with the data of the element at from of queue.
	 */
	public Data Dequeue(){
		if(this.isEmpty()){
			throw new RuntimeException("Dequeue() called on empty Queue");
		}
		
		Data data = front.data;
		if(this.length>1) {
			front = front.next;
		}else {
			front = back = null;
		}
		
		length--;
		
		System.out.printf("Removed: %s", data);
		
		return data;
	}

	/** toString operation constructs a String with all the elements of the Queue
	 * 
	 * @return <code>String</code> object with the data of the element at from of queue.
	 */
	@Override	
	public String toString(){
		
		String str = "Queue(toString): \n";
		for(Node node=front; node!=null; node=node.next){
			str += node.toString() + " ";
		}
		return str;
	}

	/**
	 *  Equals operation compares the contents of two queue an outputs a boolean flag if both queue as are equal
	 *  Returns true if this is identical to  Q, false otherwise. 
	 *  
	 *  @return boolean flag if Queues are equal.
	 */
	public boolean equals(Queue other){
		
		if(this.length != other.length) { return false; }
		
		boolean equals = true;
		Node me = this.front;
		Node you = other.front;
		
		while( equals && me != null){
			equals = (me.data.equals(you.data));
			me = me.next;
			you = you.next;
		}
		
		return equals;
	}

	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * performs the standard queue initialize operation which removes all the contents of the queue.
	 */
	public void clear() {
		
		front = back = null; length = 0; 
	}

}

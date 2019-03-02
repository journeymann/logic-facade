/**
 * 
 */
package com.research.types.trees.bplus.modern;

/**
 * @author cgordon
 * @param <E>
 *
 */
public class BPInternal<Key,E> implements BPNode<Key,E> { // Leaf node
	private int numrecords;
	private Key theKeys[];
	private BPNode<Key,E> theChildren[];
	private int MAX = 3; //initialize
	private int MIN = 1; //initialize	
	private Key key;
	private E data;	
	private boolean isleaf = false;

	public BPInternal() {

	}

	public BPInternal(Key k, E e) {
		key = k;
		data = e;
	}

	public boolean isLeaf() { return isleaf; }
	public int numrecs() { return numrecords; }
	public Key[] keys() { return theKeys; }

	public BPNode<Key,E> add(BPNode<Key,E> child) { 

		if(numrecords < MAX){
			//this node is the father
			theChildren[numrecords++] = (BPLeaf<Key,E>)child;
			return child; 
		}else{
			//determine who is the father then add child..
			BPInternal<Key,E> grandchild = new BPInternal<Key,E>();
			grandchild.add((BPLeaf<Key,E>)child);
			return grandchild;
		}
	}

	public BPNode<Key,E> add(Key k,E e) { 
		BPNode<Key,E> child = null;

		if(numrecords < MAX){
			child = new BPLeaf<Key,E>(k,e); // memory/performance efficient
			theChildren[numrecords++] =  child;
			return child; 
		}else{
			//determine who is the father then add child..
			BPInternal<Key,E> grandchild = new BPInternal<Key,E>();
			child = new BPLeaf<Key,E>(k,e); // memory/performance efficient
			grandchild.add(child);
			return grandchild;
		}
	}

	public boolean underflow(int which) { return numrecords <= MIN; }

	public BPNode<Key,E> children(int which) { return theChildren[which]; }

	public Key getKey() {

		return key;
	}

	public void setData(E e) {

		data = e;
	}

	public E getData() {

		return data;
	}

	@Override
	public String toString() {

		return String.format("node: key: %d , %s, leaf? : %s \n", key, data, isLeaf());
	}



};
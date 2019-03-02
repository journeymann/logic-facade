/**
 * 
 */
package com.research.types.trees.bplus.modern;

/**
 * @author cgordon
 * @param <E>
 *
 */
public class BPLeaf<Key,E> implements BPNode<Key,E> { // Leaf node

	private Key key;
	private E data;
	private boolean isleaf = true;

	public BPLeaf(int maxrec) {

	}

	public BPLeaf(Key k, E e) {
		key = k;
		data = e;
	}

	public boolean isLeaf() { 
		return isleaf;
	}

	public int numrecs() {
		return 0; 
	}

	public Key[] keys() { 

		return null; 
	}

	public BPLeaf<Key,E> add(Key k, E e) {

		isleaf = false;
		return new BPLeaf<Key,E>(k,e); 
	}

	public boolean delete(int k) { 

		return false;
	}

	public Key getKey() {

		return key;
	}

	public void setData(E e) {

		data = e;
	}

	public E getData() {

		return data;
	}

	public boolean underflow(int which) { return false; }


	@Override
	public String toString() {

		return String.format("node: key: %d , %s, leaf? : %s \n", key, data, isLeaf());
	}

};
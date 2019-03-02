/**
 * 
 */
package com.research.types.trees.bplus.modern;

/**
 * @author cgordon
 *
 */
/** Interface for B+ Tree nodes */
public interface BPNode<Key,E> {
	public boolean isLeaf();
	public int numrecs();
	public Key[] keys();
}

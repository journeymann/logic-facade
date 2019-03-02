/**
 * 
 */
package com.research.types.trees.bplus.modern;

import java.util.Vector;

/**
 * @author cgordon
 *
 */
/** Dictionary implemented using a B+ tree. */
public class BPTree<Key extends Comparable<? super Key>, E>
implements Dictionary<Key, E> {

	private BPNode<Key,E> root;     // Root of B+ tree
	private int nodecount;        // # of records now in 2-3 tree
	private static final int NUMRECS = 12;

	/** Stub for a binary search on the key array */
	private int binaryle(Key keys[], int cnt, Key k) { return 0; }

	public BPTree() { 
		nodecount = 0;
		root = new BPLeaf<Key,E>(NUMRECS);
	}

	public void clear() {   /** Reinitialize */
		nodecount = 0;
		root = null;
	}

	public void insert(Key k, E e) {  /** Insert an element */
		root = root==null? root = new BPLeaf<Key,E>(NUMRECS) : inserthelp(root, k, e);
		nodecount++;
	}

	/** Remove an element */
	public E remove(Key k) {
		E temp = findhelp(root, k);
		if (temp != null) {
			if (removehelp(root, k) && (root.numrecs() == 1)) // Collapse root
				if (!root.isLeaf()) root = ((BPInternal<Key,E>)root).children(0);
			nodecount--;
		}
		return temp;
	}

	/** Remove some element. */
	public E removeAny() {
		System.out.println("removeany not implemented");
		return null;
	}

	/** Find a record with key value "k" */
	public E find(Key k) {

		return root==null? null : findhelp(root, k);
	}

	/** Return number of values in the tree */
	public int size() { return nodecount; }

	/** Return flag indicating child deletion should result in node type change from internal to leaf */
	public boolean underflow(BPNode<Key,E> node) {
	
		return node.numrecs() == 1;
	}
	
	private BPNode<Key,E> inserthelp(BPNode<Key,E> rt,
			Key k, E e) {
		@SuppressWarnings("unused")
		BPNode<Key,E> retval;
		if (rt.isLeaf()) // At leaf node: insert here
			return ((BPLeaf<Key,E>)rt).add(k, e);
		// Add to internal node
		int currec = binaryle(rt.keys(), rt.numrecs(), k);
		BPNode<Key,E> temp = inserthelp(
				((BPInternal<Key,E>)root).children(currec), k, e);
		if (temp != ((BPInternal<Key,E>)rt).children(currec))
			return ((BPInternal<Key,E>)rt).
					add((BPInternal<Key,E>)temp);
		else
			return rt;
	}

	private E findhelp(BPNode<Key,E> rt, Key k) {
		int currec = binaryle(rt.keys(), rt.numrecs(), k);
		if (rt.isLeaf())
			if (((((BPLeaf<Key,E>)rt).keys())[currec]).equals(k))
				return ((BPLeaf<Key,E>)rt).getData();
			else return null;
		else
			return findhelp(((BPInternal<Key,E>)rt).
					children(currec), k);
	}

	public void print(BPNode<Key,E> node) { //traverse the b+ tree recursively

		if(node==null) { System.out.printf("Node: Tree is empty"); return; };

		if(node.isLeaf()){
			System.out.printf("Leaf Node: %s\n", node);	    
		}else {
			System.out.printf("Internal Node: %s\n", node);
			Key[] keys = node.keys();
			for(Key k : keys) {

				BPNode<Key,E> child = ((BPInternal<Key,E>)node).children((Integer)k);
				print(child);
			}	
		}
	}

	public void printTree() {
		printTree(root);
	}
	
	private void printTree(BPNode<Key,E> tree) {
        // create a vector to store all the nodes from each level as we 
        Vector<BPNode<Key,E>> nodeList = new Vector<BPNode<Key,E>>();
        
        // put the root of the tree onto the stack to start the process
        nodeList.add(tree);

        boolean done = false;
        while(! done) {
        	// this vector will hold all the children of the nodes in the current level
            Vector<BPNode<Key,E>> nextLevelList = new Vector<BPNode<Key,E>>();
            String toprint = "";
            
            // for each node in the list convert it to a string and add any children to the nextlevel stack
            for(int i=0; i < nodeList.size(); i++) {
            	
            	// get the node at position i
            	BPNode<Key,E> node = (BPNode<Key,E>)nodeList.elementAt(i);
                
                // convert the node into a string
                toprint += node.toString() + " ";
                
                // if this is a leaf node we need only print the contents
                if(node.isLeaf()) {
                    done = true;
                }
                // if this is a tree node print the contents and populate
                // the temp vector with nodes that node i points to
                else
                {
        			System.out.printf("In the Node: %s\n", node);
        			Key[] keys = node.keys();
        			for(Key k : keys) {

        				BPNode<Key,E> child = ((BPInternal<Key,E>)node).children((Integer)k);
        				nextLevelList.add(child);
        			}	
                }
            }
            
            // print the level
            System.out.printf(toprint + System.getProperty("line.separator"));
            
            // go to the next level and print it
            nodeList = nextLevelList;
        }
	}	
	/** Delete a record with the given key value, and 
    return true if the root underflows */
	private boolean removehelp(BPNode<Key,E> rt, Key k) {
		int currec = binaryle(rt.keys(), rt.numrecs(), k);
		if (rt.isLeaf())
			if ((((BPLeaf<Key,E>)rt).keys()[currec]).equals(k))
				return ((BPLeaf<Key,E>)rt).delete(currec);
			else return false;
		else // Process internal node
			if (removehelp(((BPInternal<Key,E>)rt).children(currec),
					k))
				// Child will merge if necessary
				return ((BPInternal<Key,E>)rt).underflow(currec);
			else return false;
	}

	public void print() {
		print(root);
	}

}
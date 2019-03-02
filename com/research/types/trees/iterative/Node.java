/**
 * 
 */
package com.research.types.trees.iterative;

import com.research.types.common.Data;

/**
 * @author cgordon
 *
 */
public class Node{
	Data data;
	Node left;
	Node right;	
	public Node(Data data){
		this.data = data;
		left = null;
		right = null;
	}
}